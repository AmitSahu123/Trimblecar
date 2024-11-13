package com.CRMS.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRMS.entity.Owner;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
	
	Optional<Owner> findByEmail(String email);

}
