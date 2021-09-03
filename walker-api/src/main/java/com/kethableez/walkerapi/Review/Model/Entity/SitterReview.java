package com.kethableez.walkerapi.Review.Model.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// TODO: Po wejściu na swój profil dociągać dane takie jak rating, dogs, walks, galeria etc.

@Document(collection = "sitter_reviews")
@Getter
@Setter
@RequiredArgsConstructor
public class SitterReview {
    @Id
    private String id;

    private String walkId;

    private String sitterId;
    
    private String ownerId;
    
    private String dogId;

    private Float rating;

    private String reviewBody;

    private LocalDateTime reviewDate;


    public SitterReview(String walkId, String sitterId, String ownerId, String dogId, Float rating, String reviewBody, LocalDateTime reviewDate) {
        this.walkId = walkId;
        this.sitterId = sitterId;
        this.ownerId = ownerId;
        this.dogId = dogId;
        this.rating = rating;
        this.reviewBody = reviewBody;
        this.reviewDate = reviewDate;
    }

}
