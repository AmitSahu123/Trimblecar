package com.CRMS.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
import com.CRMS.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer registeredCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.ok(registeredCustomer);
    }

    @GetMapping("/lease-history")
    public ResponseEntity<List<Lease>> getCustomerLeaseHistory(@RequestParam Long customerId) {
        List<Lease> leaseHistory = customerService.getCustomerLeaseHistory(customerId);
        return ResponseEntity.ok(leaseHistory);
    }

    @GetMapping("/active-leases")
    public ResponseEntity<List<Lease>> getActiveLeases(@RequestParam Long customerId) {
        List<Lease> activeLeases = customerService.getActiveLeases(customerId);
        return ResponseEntity.ok(activeLeases);
    }
}