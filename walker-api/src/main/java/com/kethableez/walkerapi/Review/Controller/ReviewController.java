package com.kethableez.walkerapi.Review.Controller;

import java.util.List;

import com.kethableez.walkerapi.Review.Model.Entity.DogReview;
import com.kethableez.walkerapi.Review.Model.Entity.SitterReview;
import com.kethableez.walkerapi.Review.Model.Request.DogReviewRequest;
import com.kethableez.walkerapi.Review.Model.Request.SitterReviewRequest;
import com.kethableez.walkerapi.Review.Service.ReviewService;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

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
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final UserService userService;

    @PostMapping("/sitter/add")
    public ResponseEntity<?> addSitterReview(UsernamePasswordAuthenticationToken token, String walkId, SitterReviewRequest request) {
        ActionResponse response = reviewService.addSitterReview(userService.getIdFromToken(token), walkId, request);

        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/dog/add")
    public ResponseEntity<?> addDogReview(UsernamePasswordAuthenticationToken token, String walkId, DogReviewRequest request) {
        ActionResponse response = reviewService.addDogReview(userService.getIdFromToken(token), walkId, request);
        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @GetMapping("/sitter/{sitterId}")
    public ResponseEntity<List<SitterReview>> getUserReviews(@PathVariable("sitterId") String sitterId) {
        List<SitterReview> reviews = reviewService.getSitterReview(sitterId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/dog/{dogId}")
    public ResponseEntity<List<DogReview>> getDogReviews(@PathVariable("dogId") String dogId) {
        List<DogReview> reviews = reviewService.getDogReview(dogId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
