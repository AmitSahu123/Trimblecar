package com.CRMS.Controller;

import com.CRMS.entity.Owner;
import com.CRMS.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner) {
        Owner registeredOwner = ownerService.registerOwner(owner);
        return ResponseEntity.ok(registeredOwner);
    }

    @GetMapping
    public ResponseEntity<Owner> getOwnerById(@RequestParam Long ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        return ResponseEntity.ok(owner);
    }
}
