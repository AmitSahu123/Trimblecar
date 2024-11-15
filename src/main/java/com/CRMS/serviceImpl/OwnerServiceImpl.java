package com.CRMS.serviceImpl;

import com.CRMS.eception.ResourceNotFoundException;
import com.CRMS.entity.Owner;
import com.CRMS.repository.OwnerRepository;
import com.CRMS.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner registerOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not Found with ID: " + ownerId));
    }

}
