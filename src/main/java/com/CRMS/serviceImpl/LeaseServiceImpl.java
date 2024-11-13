package com.CRMS.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRMS.eception.LeaseLimitExceededException;
import com.CRMS.eception.ResourceNotFoundException;
import com.CRMS.entity.Car;
import com.CRMS.entity.CarStatus;
import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
import com.CRMS.entity.LeaseStatus;
import com.CRMS.repository.CarRepository;
import com.CRMS.repository.CustomerRepository;
import com.CRMS.repository.LeaseRepository;
import com.CRMS.service.LeaseService;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Lease startLease(Long carId, Long customerId) {
        // Check if customer already has 2 active leases
        if (customerRepository.findActiveLeasesByCustomerId(customerId).size() >= 2) {
            throw new LeaseLimitExceededException("Customer cannot have more than 2 active leases.");
        }
        
        Car car = carRepository.findById(carId)
            .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + carId));
        
        if (car.getStatus() != CarStatus.IDLE) {
            throw new IllegalArgumentException("Car is not available for lease.");
        }

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        // Create and save new lease
        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(customer);
        lease.setStartDate(LocalDate.now());
        lease.setStatus(LeaseStatus.ACTIVE);
        car.setStatus(CarStatus.ON_LEASE);
        carRepository.save(car);

        return leaseRepository.save(lease);
    }

    @Override
    public void endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId)
            .orElseThrow(() -> new ResourceNotFoundException("Lease not found with ID: " + leaseId));

        lease.setEndDate(LocalDate.now());
        lease.setStatus(LeaseStatus.ENDED);

        Car car = lease.getCar();
        car.setStatus(CarStatus.IDLE);
        carRepository.save(car);

        leaseRepository.save(lease);
    }

    @Override
    public List<Lease> getLeaseHistoryByCar(Long carId) {
        return leaseRepository.findByCarId(carId);
    }
}