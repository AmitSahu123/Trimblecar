package com.CRMS.repository;

import com.CRMS.entity.Lease;
import com.CRMS.entity.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> findByCarId(Long carId);

    List<Lease> findByCustomerId(Long customerId);


    @Query("SELECT l FROM Lease l WHERE l.car.id = :carId AND l.status = :status")
    List<Lease> findActiveLeaseByCarId(Long carId, LeaseStatus status); // status is of type LeaseStatus, not String

    List<Lease> findByCustomerIdOrderByStartDateDesc(Long customerId);

    List<Lease> findByCarIdAndStatus(Long carId, LeaseStatus status); // Corrected parameter types (LeaseStatus for status)
}
