package com.CRMS.service;

import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Customer registerCustomer(Customer customer);

    List<Lease> getCustomerLeaseHistory(Long customerId);

    List<Lease> getActiveLeases(Long customerId);


}
