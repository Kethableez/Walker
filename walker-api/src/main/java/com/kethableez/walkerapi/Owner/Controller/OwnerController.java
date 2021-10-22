package com.kethableez.walkerapi.Owner.Controller;

import java.util.ArrayList;
import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Service.DogService;
import com.kethableez.walkerapi.Image.Service.ImageService;
import com.kethableez.walkerapi.Owner.Model.Owner;
import com.kethableez.walkerapi.Owner.Service.OwnerService;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Walk.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private final UserService userService;

    @Autowired
    private final ImageService imageService;

    @Autowired
    private final OwnerService ownerService;

    @GetMapping("/ownerData")
    public ResponseEntity<Owner> getOwnerData(UsernamePasswordAuthenticationToken token) {
        Owner owner = ownerService.getData(userService.getIdFromToken(token));
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/ownerData/{username}")
    public ResponseEntity<Owner> getOwnerData(@PathVariable String username) {
        Owner owner = ownerService.getData(userService.getIdFromUsername(username));
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<DogCard>> getDogs(UsernamePasswordAuthenticationToken token) {
        List<DogCard> dogCards = this.dogService.getOwnerDogs(userService.getIdFromToken(token));
        return new ResponseEntity<>(dogCards, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<WalkCard>> getWalks(UsernamePasswordAuthenticationToken token) {
        List<WalkCard> walks = this.walkService.getOwnerIncomingWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<WalkCard>> getOwnerHistory(UsernamePasswordAuthenticationToken token) {
        List<WalkCard> history = this.walkService.getOwnerPastWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<List<String>> getImages(UsernamePasswordAuthenticationToken token) {
        List<String> images = new ArrayList<>();
        for (DogCard d : this.dogService.getOwnerDogs(userService.getIdFromToken(token))) {
            images.addAll(imageService.getDogReviewImages(d.getDog().getId()));
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}