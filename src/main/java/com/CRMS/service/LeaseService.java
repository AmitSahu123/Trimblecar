package com.CRMS.service;

import com.CRMS.entity.Lease;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaseService {

    Lease startLease(Long carId, Long customerId);

    void endLease(Long leaseId);

    List<Lease> getLeaseHistoryByCar(Long carId);

}
