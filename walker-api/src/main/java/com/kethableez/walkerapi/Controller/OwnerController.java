package com.kethableez.walkerapi.Controller;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Request.DogRequest;
import com.kethableez.walkerapi.Request.WalkRequest;
import com.kethableez.walkerapi.Response.MessageResponse;
import com.kethableez.walkerapi.Service.DogService;
import com.kethableez.walkerapi.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {
    
    @Autowired
    private final DogService dogService;

    @Autowired
    private final WalkService walkService;
    
    @PostMapping("/create_dog")
    public ResponseEntity<?> createDog(@RequestBody DogRequest request ,UsernamePasswordAuthenticationToken token) {
        this.dogService.createDog(token, request);
        return ResponseEntity.ok(new MessageResponse("Dodano zwierzaka!"));
    }

    @GetMapping("/owner_dogs")
    public ResponseEntity<List<Dog>> getDogs(UsernamePasswordAuthenticationToken token) {
        List<Dog> dogs = this.dogService.getUserDogs(token);
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @PostMapping("/create_walk")
    public ResponseEntity<?> createWalk(@RequestBody WalkRequest request) {
        walkService.createWalk(request);
        return ResponseEntity.ok(new MessageResponse("Stworzono spacer!"));
    }

    @GetMapping("/walks")
    public ResponseEntity<List<Walk>> getAllWalks() {
        List<Walk> walks = walkService.getAll();
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }
}
