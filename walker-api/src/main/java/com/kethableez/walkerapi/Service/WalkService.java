package com.kethableez.walkerapi.Service;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;
import com.kethableez.walkerapi.Request.WalkRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalkService {
    
    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final DogRepository dogRepository;

    public Walk createWalk(WalkRequest request){
        Dog dog = dogRepository.getById(request.getDogId());

        Walk walk = new Walk(
            dog,
            request.getWalkDateTime(),
            request.getCity(),
            request.getAddress(),
            request.getWalkLat(),
            request.getWalkLon(),
            request.getWalkDescription(),
            false
        );

        return walkRepository.save(walk);
    }

    public List<Walk> getAll(){
        return walkRepository.findAll();
    }

}
