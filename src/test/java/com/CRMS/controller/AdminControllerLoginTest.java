package com.CRMS.controller;



import com.CRMS.Controller.AdminController;
import com.CRMS.entity.Admin;
import com.CRMS.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerLoginTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testLoginAdminSuccess() throws Exception {
        Admin admin = new Admin();
        admin.setEmail("prahlad@.com");
        admin.setPwd("sarkar");

        // Mock the authentication manager to simulate a successful authentication
        Authentication authentication = mock(Authentication.class);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);

        // Mock the JWT service to generate a token
        when(jwtService.generateToken(anyString())).thenReturn("mock-jwt-token");

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"email\":\"prahlad@.com\",\"pwd\":\"sarkar\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("mock-jwt-token"));
    }

    @Test
    void testLoginAdminFailure() throws Exception {
        Admin admin = new Admin();
        admin.setEmail("prahlad@.com");
        admin.setPwd("wrong-password");

        // Mock the authentication manager to simulate a failed authentication
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"email\":\"prahlad@.com\",\"pwd\":\"wrong-password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Credentials"));
    }
}
