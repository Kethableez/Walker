package com.kethableez.walkerapi.Owner.Service;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.Owner.Model.Owner;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

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