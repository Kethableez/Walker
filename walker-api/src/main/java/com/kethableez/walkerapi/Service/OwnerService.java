package com.kethableez.walkerapi.Service;

import java.util.List;

import com.kethableez.walkerapi.Model.DTO.Owner;
import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final WalkRepository walkRepository;

    public Owner getData(String ownerId) {
        User user = userRepository.findById(ownerId).orElseThrow();
        List<Dog> dogs = dogRepository.findByOwnerId(ownerId);
        List<Walk> walks = walkRepository.findByOwnerId(ownerId);

        Owner owner = new Owner(user, dogs, walks);
        return owner;
    }
}