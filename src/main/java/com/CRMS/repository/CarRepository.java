package com.CRMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CRMS.entity.Car;
import com.CRMS.entity.CarStatus;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
	
	List<Car> findBystatus(String status);
	
	
	List<Car> findByOwnerId(Long ownerId);
	
	
	@Query("SELECT c FROM Car c WHERE c.status = 'IDLE'")
	List<Car> findAvailableCars();
	

}
