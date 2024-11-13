package com.CRMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByEmail(String email);
	
	
	@Query("SELECT l FROM Lease l WHERE l.customer.id = :customerId AND l.status = 'ACTIVE'")
	List<Lease> findActiveLeasesByCustomerId(@Param("customerId") Long customerId);

}
