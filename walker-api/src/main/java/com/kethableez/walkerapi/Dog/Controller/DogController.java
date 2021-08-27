package com.kethableez.walkerapi.Dog.Controller;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Model.Request.DogRequest;
import com.kethableez.walkerapi.Dog.Service.DogService;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<DogCard> getDogInfo(@PathVariable("id") String dogId) {
        DogCard dog = this.dogService.createDogCard(this.dogService.getDogFromId(dogId));
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> createDog(@RequestBody DogRequest request, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = this.dogService.createDog(token, request);

        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @DeleteMapping("/delete_dog/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteDog(@PathVariable("id") String dogId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = dogService.deleteDog(dogId, userService.getIdFromToken(token));

        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }
}
