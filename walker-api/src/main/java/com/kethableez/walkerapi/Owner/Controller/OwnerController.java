package com.kethableez.walkerapi.Owner.Controller;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Service.DogService;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Walk.Model.DTO.PastWalkCard;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/dogs")
    public ResponseEntity<List<DogCard>> getDogs(UsernamePasswordAuthenticationToken token) {
        List<DogCard> dogCards = this.dogService.getOwnerDogs(userService.getIdFromToken(token));
        return new ResponseEntity<>(dogCards, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<Walk>> getWalks(UsernamePasswordAuthenticationToken token) {
        List<Walk> walks = this.walkService.getOwnerWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/sitters")
    public ResponseEntity<List<UserInfo>> getSitters(UsernamePasswordAuthenticationToken token) {
        List<UserInfo> sitters = this.walkService.getSitters(userService.getIdFromToken(token));
        return new ResponseEntity<>(sitters, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<PastWalkCard>> getOwnerHistory(UsernamePasswordAuthenticationToken token) {
        List<PastWalkCard> history = this.walkService.getOwnerHistory(userService.getIdFromToken(token));
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}