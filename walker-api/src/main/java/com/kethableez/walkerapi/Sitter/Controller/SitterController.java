package com.kethableez.walkerapi.Sitter.Controller;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Service.DogService;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;
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
@RequestMapping("/sitter")
@RequiredArgsConstructor
public class SitterController {

    @Autowired
    private final WalkService walkService;

    @Autowired
    private final DogService dogService;

    @Autowired
    private final UserService userService;

    @GetMapping("/dogs")
    public ResponseEntity<List<DogCard>> getSitterDogs(UsernamePasswordAuthenticationToken token) {
        List<DogCard> dogs = this.dogService.getSitterDogCards(userService.getIdFromToken(token));
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<WalkInfo>> getSitterWalks(UsernamePasswordAuthenticationToken token) {
        List<WalkInfo> walks = this.walkService.getSitterWalkInfos(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<WalkCard>> getWalkHistory(UsernamePasswordAuthenticationToken token) {
        List<WalkCard> walks = walkService.getSitterHistoryWalkCards(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }
}
