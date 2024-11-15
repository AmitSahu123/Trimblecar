package com.CRMS.service;

import com.CRMS.entity.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    Owner registerOwner(Owner owner);

    Owner getOwnerById(Long ownerId);

}
