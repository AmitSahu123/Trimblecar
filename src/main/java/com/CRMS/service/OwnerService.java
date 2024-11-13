package com.CRMS.service;

import org.springframework.stereotype.Service;

import com.CRMS.entity.Owner;
@Service
public interface OwnerService {
	Owner registerOwner(Owner owner);
	Owner getOwnerById(Long ownerId);

}
