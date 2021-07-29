package com.kethableez.walkerapi.Controller;

import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Response.MessageResponse;
import com.kethableez.walkerapi.Service.SitterService;
import com.kethableez.walkerapi.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final SitterService sitterService;

    @Autowired
    private final WalkService walkService;

    @PostMapping("/walk_enroll/{id}")
    public ResponseEntity<?> enroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        this.sitterService.enrollToWalk(token, walkId);
        return ResponseEntity.ok(new MessageResponse("Zapisano na spacer"));
    }

    @DeleteMapping("/walk_disenroll/{id}")
    public ResponseEntity<?> disenroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        this.sitterService.disenrollFromWalk(token, walkId);
        return ResponseEntity.ok(new MessageResponse("Wypisano ze spaceru"));
    }

    @GetMapping("/dogs")
    public ResponseEntity<Set<Dog>> getDogs(UsernamePasswordAuthenticationToken token) {
        Set<Dog> dogs = this.sitterService.getDogs(token);
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<Walk>> getWalks() {
        List<Walk> walks = walkService.getWalks();
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/walk_history")
    public ResponseEntity<List<Walk>> getWalkHistory(UsernamePasswordAuthenticationToken token) {
        List<Walk> walks = walkService.getHistoryWalks(token);
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }
    
}
