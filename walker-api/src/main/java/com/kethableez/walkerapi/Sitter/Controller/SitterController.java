package com.kethableez.walkerapi.Sitter.Controller;

import java.util.List;

import com.kethableez.walkerapi.Image.Service.ImageService;
import com.kethableez.walkerapi.Review.Model.DTO.SitterReviewCard;
import com.kethableez.walkerapi.Review.Service.ReviewService;
import com.kethableez.walkerapi.Sitter.Model.Sitter;
import com.kethableez.walkerapi.Sitter.Service.SitterService;
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
@RequestMapping("/sitter")
@RequiredArgsConstructor
public class SitterController {

    @Autowired
    private final SitterService sitterService;

    @Autowired
    private final WalkService walkService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final ImageService imageService;

    @GetMapping("/sitterData")
    public ResponseEntity<Sitter> getSitterData(UsernamePasswordAuthenticationToken token) {
        Sitter sitter = sitterService.getSitterData(userService.getIdFromToken(token));
        return new ResponseEntity<>(sitter, HttpStatus.OK);
    }

    @GetMapping("/sitterData/{username}")
    public ResponseEntity<Sitter> getSitterData(@PathVariable String username) {
        Sitter sitter = sitterService.getSitterData(userService.getIdFromUsername(username));
        return new ResponseEntity<>(sitter, HttpStatus.OK);
    }

    @GetMapping("/walks")
    public ResponseEntity<List<WalkCard>> getSitterWalks(UsernamePasswordAuthenticationToken token) {
        List<WalkCard> walks = this.walkService.getSitterIncomingWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<WalkCard>> getWalkHistory(UsernamePasswordAuthenticationToken token) {
        List<WalkCard> walks = walkService.getSitterPastWalks(userService.getIdFromToken(token));
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<SitterReviewCard>> getReviews(UsernamePasswordAuthenticationToken token) {
        List<SitterReviewCard> reviews = reviewService.getSitterReviewCard(userService.getIdFromToken(token));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<List<String>> getImages(UsernamePasswordAuthenticationToken token) {
        List<String> images = imageService.getSitterReviewImages(userService.getIdFromToken(token));
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
