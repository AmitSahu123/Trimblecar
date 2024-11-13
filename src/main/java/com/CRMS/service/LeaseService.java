package com.CRMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CRMS.entity.Lease;
@Service
public interface LeaseService {
	
	Lease startLease(Long carId,Long customerId);
	void endLease(Long leaseId);
	List<Lease> getLeaseHistoryByCar(Long carId);

}
