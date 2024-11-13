package com.CRMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CRMS.entity.Car;
import com.CRMS.entity.CarStatus;

@Service
public interface CarService {

	Car registerCar(Car car, Long ownerId);

	List<Car> getCarsByOwnerId(Long ownerId);

	void updateCarStatus(Long carId, CarStatus status);

	List<Car> getAvailableCars();

}
