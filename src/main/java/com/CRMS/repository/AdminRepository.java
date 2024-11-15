package com.CRMS.repository;


import com.CRMS.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByEmail(String email);

}