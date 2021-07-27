package com.kethableez.walkerapi.Service;

import java.util.HashSet;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
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

    public Sitter createSitter(User user) {
        Sitter newSitter = new Sitter(
                user
        );
        return sitterRepository.save(newSitter);
    }

    public Sitter getData(Long userId){
        return this.sitterRepository.findByUserId(userId);
    }

    public Sitter enrollToWalk(UsernamePasswordAuthenticationToken token, Long walkId) {
        Sitter sitter = sitterRepository.findByUsername(token.getName());
        Walk walk = walkRepository.getById(walkId);

        walk.setBooked(true);
        sitter.getWalks().add(walk);
        walkRepository.save(walk);
        return sitterRepository.save(sitter);
    }

    public Sitter disenrollFromWalk(UsernamePasswordAuthenticationToken token, Long walkId) {
        Sitter sitter = sitterRepository.findByUsername(token.getName());
        Walk walk = walkRepository.getById(walkId);

        sitter.getWalks().remove(walk);
        walk.setBooked(false);
        walkRepository.save(walk);
        return sitterRepository.save(sitter);
    }

    public Set<Dog> getDogs(UsernamePasswordAuthenticationToken token) {
        Sitter sitter = sitterRepository.findByUsername(token.getName());
        Set<Dog> dogs = new HashSet<>();
        for (Walk w : sitter.getWalks()) {
            dogs.add(w.getDog());
        }

        return dogs;
    }
}
