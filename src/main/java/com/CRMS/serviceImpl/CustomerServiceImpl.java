package com.CRMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
import com.CRMS.repository.CustomerRepository;
import com.CRMS.repository.LeaseRepository;
import com.CRMS.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LeaseRepository leaseRepository;

	@Override
	public Customer registerCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Lease> getCustomerLeaseHistory(Long customerId) {
		// TODO Auto-generated method stub
		return leaseRepository.findByCustomerIdOrderByStartDateDesc(customerId);
	}

	@Override
	public List<Lease> getActiveLeases(Long customerId) {
		return customerRepository.findActiveLeasesByCustomerId(customerId);
	}

}
