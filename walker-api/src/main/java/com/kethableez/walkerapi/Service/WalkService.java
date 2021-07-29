package com.kethableez.walkerapi.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.OwnerRepository;
import com.kethableez.walkerapi.Repository.SitterRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;
import com.kethableez.walkerapi.Request.WalkRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalkService {

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final OwnerRepository ownerRepository;

    @Autowired
    private final SitterRepository sitterRepository;

    public Walk createWalk(WalkRequest request) {
        Dog dog = dogRepository.findById(request.getDogId()).orElseThrow();

        Walk walk = new Walk(dog, request.getWalkDateTime(), request.getCity(), request.getAddress(),
                request.getWalkLat(), request.getWalkLon(), request.getWalkDescription(), false);

        return walkRepository.save(walk);
    }

    public List<Walk> getOwnerWalks(UsernamePasswordAuthenticationToken token) {
        return walkRepository.findByOwnerId(ownerRepository.findByUsername(token.getName()).orElseThrow().getId());
    }

    public List<Sitter> getSitters(UsernamePasswordAuthenticationToken token) {
        Set<String> sitterIds = new HashSet<>();

        for (Walk w : this.getOwnerWalks(token)) {
            if (w.isBooked()) sitterIds.add(w.getSitterId());
        }

        List<Sitter> sitters = new ArrayList<>();
        for (String id : sitterIds) {
            sitters.add(sitterRepository.findById(id).get());
        }

        return sitters;
    }

    public List<Walk> getDogWalks(String dogId) {
        return walkRepository.findBydogId(dogId);
    }

    public void deleteWalk(String walkId) {
        this.deleteFromUser(walkId);
        this.walkRepository.deleteById(walkId);
    }

    public void deleteFromUser(String walkId) {
        Walk walk = walkRepository.findById(walkId).orElseThrow();
        Sitter sitter = sitterRepository.findById(walk.getSitterId()).orElseThrow();

        sitter.getWalks().removeIf(w -> (w.equals(walk)));

        sitterRepository.save(sitter);

        walkRepository.save(walk);
    }

    public List<Walk> getWalks() {
        return this.walkRepository.findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime.now(), false);
    }

    public List<Walk> getHistoryWalks(UsernamePasswordAuthenticationToken token) {
        Sitter sitter = sitterRepository.findByUsername(token.getName()).orElseThrow();
        return this.walkRepository.findByWalkDateTimeLessThanAndSitterId(LocalDateTime.now(), sitter.getId());
    }

}
