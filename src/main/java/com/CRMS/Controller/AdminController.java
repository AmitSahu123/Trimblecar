package com.CRMS.Controller;

import com.CRMS.entity.Admin;
import com.CRMS.repository.AdminRepository;
import com.CRMS.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private AuthenticationManager authmanager;

    @Autowired
    private JWTService jwtservice;


    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody Admin admin) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(admin.getEmail(),
                admin.getPwd());
        try {
            Authentication authenticate = authmanager.authenticate(token);
            if (authenticate.isAuthenticated()) {

                String jwt = jwtservice.generateToken(admin.getEmail());
                return new ResponseEntity<>(jwt, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")

    public ResponseEntity<String> saveCustomer(@RequestBody Admin admin) {

        String encodedPwd = pwdEncoder.encode(admin.getPwd());
        admin.setPwd(encodedPwd);

        adminRepo.save(admin);

        return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);

    }

}