package com.CRMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CRMS.entity.Customer;
import com.CRMS.entity.Lease;
@Service
public interface CustomerService {
	
	Customer registerCustomer(Customer customer);
	List<Lease> getCustomerLeaseHistory(Long customerId);
	List<Lease> getActiveLeases(Long customerId);
	

}
