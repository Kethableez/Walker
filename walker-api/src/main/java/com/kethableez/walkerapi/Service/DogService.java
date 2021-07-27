package com.kethableez.walkerapi.Service;

import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.OwnerRepository;
// import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Request.DogRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DogService {
    
    @Autowired
    private final DogRepository dogRepository;

    // @Autowired
    // private final UserRepository userRepository;

    @Autowired
    private final OwnerRepository ownerRepository;

    public void createDog(UsernamePasswordAuthenticationToken token, DogRequest request) {
        Owner owner = ownerRepository.findByUsername(token.getName());
        Dog dog = new Dog(
            owner.getUser().getUsername(),
            request.getName(),
            request.getDogBreed(),
            request.getDogType(),
            request.getWalkDuration(),
            request.getCharacteristic(),
            request.getWalkIntensity(),
            request.getWalkDescription(),
            request.getDogPhoto()
        );

        dogRepository.save(dog);
        Set<Dog> dogs = owner.getDogs();
        dogs.add(dog);
        owner.setDogs(dogs);
        ownerRepository.save(owner);
    }

    public List<Dog> getUserDogs(UsernamePasswordAuthenticationToken token) {
        return dogRepository.findByUserId(token.getName());
    }

    public void deleteDog(Long dogId) {
        this.dogRepository.deleteById(dogId);
    }

    public void deleteDogFromOnwer(Long dogId) {
        this.dogRepository.deleteFromOwnerDogs(dogId);
    }
}
