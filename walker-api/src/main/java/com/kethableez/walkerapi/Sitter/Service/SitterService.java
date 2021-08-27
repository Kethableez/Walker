package com.kethableez.walkerapi.Sitter.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Service.DogService;
import com.kethableez.walkerapi.Review.Service.ReviewService;
import com.kethableez.walkerapi.Sitter.Model.Sitter;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SitterService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ReviewService reviewService;

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

        double rating = reviewService.getSitterReview(sitterId).stream().mapToDouble(r -> r.getRating()).sum();
        
        Sitter sitter = new Sitter(user, dogs, walks, (float)rating);
        return sitter;
    }

}
