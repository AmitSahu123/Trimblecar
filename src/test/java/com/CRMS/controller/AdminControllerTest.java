package com.CRMS.controller;



import com.CRMS.Controller.AdminController;
import com.CRMS.entity.Admin;
import com.CRMS.repository.AdminRepository;
import com.CRMS.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private AdminRepository adminRepo;

    @Mock
    private PasswordEncoder pwdEncoder;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testRegisterAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setName("prahlad");
        admin.setEmail("prahlad@.com");
        admin.setPwd("sarkar");
        admin.setPhno(9589022386L);

        // Mock the repository save method
        when(adminRepo.save(any(Admin.class))).thenReturn(admin);

        mockMvc.perform(post("/api/admin/register")
                        .contentType("application/json")
                        .content("{\"name\":\"prahlad\",\"email\":\"prahlad@.com\",\"pwd\":\"sarkar\",\"phno\":9589022386}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Customer Registered"));

        verify(adminRepo, times(1)).save(any(Admin.class));  // Verify that save was called
    }
}

