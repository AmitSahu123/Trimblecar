package com.CRMS.Controller;

import com.CRMS.entity.Car;
import com.CRMS.entity.CarStatus;
import com.CRMS.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/register")
    public ResponseEntity<Car> registerCar(@RequestParam Long ownerId, @RequestBody Car car) {
        Car registeredCar = carService.registerCar(car, ownerId);
        return ResponseEntity.ok(registeredCar);
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("/owner")
    public ResponseEntity<List<Car>> getCarsByOwner(@RequestParam Long ownerId) {
        List<Car> cars = carService.getCarsByOwnerId(ownerId);
        return ResponseEntity.ok(cars);
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updateCarStatus(@RequestParam Long carId, @RequestParam CarStatus status) {
        carService.updateCarStatus(carId, status);
        return ResponseEntity.ok().build();
    }
}