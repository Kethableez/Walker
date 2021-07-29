package com.kethableez.walkerapi.Service;

import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Repository.OwnerRepository;
import com.kethableez.walkerapi.Request.UserDataRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private final OwnerRepository ownerRepository;

    public Owner createOwner(User user) {
        Owner newOwner = new Owner(user);
        return ownerRepository.save(newOwner);
    }

    public Owner getData(String username) {
        return this.ownerRepository.findByUsername(username).orElseThrow();
    }

    public void changeData(User updatedUser) {
        Owner owner = ownerRepository.findByUsername(updatedUser.getUsername()).orElseThrow();
        owner.setUser(updatedUser);
        ownerRepository.save(owner);
    }
}