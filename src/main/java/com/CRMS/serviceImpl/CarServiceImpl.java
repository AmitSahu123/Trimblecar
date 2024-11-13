package com.CRMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRMS.eception.ResourceNotFoundException;
import com.CRMS.entity.Car;
import com.CRMS.entity.CarStatus;
import com.CRMS.entity.Owner;
import com.CRMS.repository.CarRepository;
import com.CRMS.repository.OwnerRepository;
import com.CRMS.service.CarService;
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerrepository;
	
	

	@Override
	public Car registerCar(Car car, Long ownerId) {
		Owner owner = ownerrepository.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not Found with ID: " + ownerId));

		car.setOwner(owner);
		car.setStatus(CarStatus.IDLE);

		return carRepository.save(car);
	}

	

	@Override
	public List<Car> getCarsByOwnerId(Long ownerId) {
		return carRepository.findByOwnerId(ownerId);
	}

	@Override
	public void updateCarStatus(Long carId, CarStatus status) {

		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + carId));

		car.setStatus(status);
		carRepository.save(car);

	}

	@Override
	public List<Car> getAvailableCars() {
		return carRepository.findBystatus("IDLE");
	}

}
