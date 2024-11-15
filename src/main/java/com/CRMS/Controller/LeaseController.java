package com.CRMS.Controller;

import com.CRMS.entity.Lease;
import com.CRMS.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @PostMapping("/start")
    public ResponseEntity<Lease> startLease(@RequestParam Long carId, @RequestParam Long customerId) {
        Lease lease = leaseService.startLease(carId, customerId);
        return ResponseEntity.ok(lease);
    }

    @PutMapping("/end")
    public ResponseEntity<Void> endLease(@RequestParam Long leaseId) {
        leaseService.endLease(leaseId);
        return ResponseEntity.ok().build();
    }
}