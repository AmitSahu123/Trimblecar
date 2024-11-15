package com.CRMS.serviceImpl;


import com.CRMS.entity.Admin;
import com.CRMS.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AdminServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin c = adminRepo.findByEmail(email);

        return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
    }

}
