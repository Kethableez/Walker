package com.kethableez.walkerapi.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.OwnerRepository;
import com.kethableez.walkerapi.Repository.SitterRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private final OwnerRepository ownerRepository;

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final SitterRepository sitterRepository;

    public Owner createOwner(User user) {
        Owner newOwner = new Owner(
                user
        );
        return ownerRepository.save(newOwner);
    }

    public Owner getData(Long userId){
        return this.ownerRepository.findByUserId(userId);
    }

    public List<Walk> getWalks(UsernamePasswordAuthenticationToken token) {
        List<Walk> walks = new ArrayList<>();
        for (Dog dog : this.ownerRepository.findByUsername(token.getName()).getDogs()) {
            walks.addAll(walkRepository.selectWallksByDogId(dog.getId()));
        }
        return walks;
    }

    public Set<Sitter> getSitters(UsernamePasswordAuthenticationToken token) {
        Set<Long> sitterIds = new HashSet<>();
        for (Walk walk : getWalks(token)) {
            if (walk.isBooked()) sitterIds.add(walkRepository.getSitter(walk.getId()));
        }

        Set<Sitter> sitters = new HashSet<>();
        for (Long id : sitterIds) {
            sitters.add(sitterRepository.getById(id));
        }
        return sitters;
    }
}
