package com.kethableez.walkerapi.Controller;

import java.util.List;

import com.kethableez.walkerapi.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    @Autowired
    private final WalkService walkService;

    @GetMapping("/all")
    public ResponseEntity<List<WalkCard>> getWalks() {
        List<WalkCard> walks = walkService.getWalkCards();
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalkCard> getWalkInfo(@PathVariable("id") String walkId) {
        WalkCard walk = this.walkService.createCard(this.walkService.getWalkFromId(walkId));
        return new ResponseEntity<>(walk, HttpStatus.OK);
    }
}
