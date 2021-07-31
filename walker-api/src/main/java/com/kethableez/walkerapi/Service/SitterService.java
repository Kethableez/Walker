package com.kethableez.walkerapi.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.DTO.Sitter;
import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SitterService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final WalkService walkService;;

    @Autowired
    private final DogService dogService;

    public Sitter getData(String sitterId) {
        User user = userRepository.findById(sitterId).orElseThrow();
        List<Walk> walks = walkService.getSitterWalks(sitterId);

        Set<String> dogIds = new HashSet<>();
        for (Walk w : walks) dogIds.add(w.getId());

        List<Dog> dogs = dogService.getSitterDogs(sitterId);

        Sitter sitter = new Sitter(user, dogs, walks);
        return sitter;
    }

}
