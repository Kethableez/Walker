package com.kethableez.walkerapi.Service;

import java.util.HashSet;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.SitterRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SitterService {
    @Autowired
    private final SitterRepository sitterRepository;

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final DogRepository dogRepository;

    public Sitter createSitter(User user) {
        Sitter newSitter = new Sitter(user);
        return sitterRepository.save(newSitter);
    }

    public Sitter getData(String username) {
        return this.sitterRepository.findByUsername(username).orElseThrow();
    }

    public void enrollToWalk(UsernamePasswordAuthenticationToken token, String walkId) {
        Sitter sitter = sitterRepository.findByUsername(token.getName()).orElseThrow();
        Walk walk = walkRepository.findById(walkId).orElseThrow();
        sitter.getWalks().add(walk);

        walk.setBooked(true);
        walk.setSitterId(sitter.getId());
        walkRepository.save(walk);
        sitterRepository.save(sitter);
    }

    public void disenrollFromWalk(UsernamePasswordAuthenticationToken token, String walkId) {
        Sitter sitter = sitterRepository.findByUsername(token.getName()).orElseThrow();
        Walk walk = walkRepository.findById(walkId).orElseThrow();

        sitter.getWalks().removeIf(w -> (w.equals(walk)));

        walk.setBooked(false);
        walk.setSitterId("");
        walkRepository.save(walk);
        sitterRepository.save(sitter);
    }

    public Set<Walk> test(UsernamePasswordAuthenticationToken token) {
        Sitter sitter = sitterRepository.findByUsername(token.getName()).orElseThrow();
        Set<Walk> walks = sitter.getWalks();
        return walks;
    }

    public void changeData(User updatedUser) {
        Sitter sitter = sitterRepository.findByUsername(updatedUser.getUsername()).orElseThrow();
        sitter.setUser(updatedUser);
        sitterRepository.save(sitter);
    }

    public Set<Dog> getDogs(UsernamePasswordAuthenticationToken token) {
        Sitter sitter = sitterRepository.findByUsername(token.getName()).orElseThrow();
        Set<String> dogIds = new HashSet<>();
        for (Walk w : sitter.getWalks()) {
            dogIds.add(w.getDog().getId());
        }
        Set<Dog> dogs = new HashSet<>();
        for (String id : dogIds) {
            dogs.add(dogRepository.findById(id).get());
        }

        return dogs;
    }
}
