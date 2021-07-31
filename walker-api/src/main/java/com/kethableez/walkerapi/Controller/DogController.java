package com.kethableez.walkerapi.Controller;

import com.kethableez.walkerapi.Model.DTO.DogCard;
import com.kethableez.walkerapi.Service.DogService;

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
@RequestMapping("/dog")
@RequiredArgsConstructor
public class DogController {
    
    @Autowired
    private final DogService dogService;

    @GetMapping("/{id}")
    public ResponseEntity<DogCard> getDogInfo(@PathVariable("id") String dogId) {
        DogCard dog = this.dogService.createDogCard(this.dogService.getDogFromId(dogId));
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }
}
