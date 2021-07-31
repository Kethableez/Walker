package com.kethableez.walkerapi.Controller;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Model.Response.ActionResponse;
import com.kethableez.walkerapi.Model.Response.MessageResponse;
import com.kethableez.walkerapi.Service.DogService;
import com.kethableez.walkerapi.Service.UserService;
import com.kethableez.walkerapi.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/enroll/{id}")
    public ResponseEntity<?> enroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.walkEnroll(token, walkId);

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/disenroll/{id}")
    public ResponseEntity<?> disenroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.walkDisenroll(token, walkId);

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getSitterDogs(UsernamePasswordAuthenticationToken token) {
        List<Dog> dogs = this.dogService.getSitterDogs(userService.getIdFromToken(token));
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<Walk>> getSitterWalks(UsernamePasswordAuthenticationToken token) {
        List<Walk> walks = this.walkService.getSitterWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Walk>> getWalkHistory(UsernamePasswordAuthenticationToken token) {
        List<Walk> walks = walkService.getSitterHistoryWalk(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }
}
