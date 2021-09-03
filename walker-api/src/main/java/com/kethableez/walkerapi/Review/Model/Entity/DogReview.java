package com.kethableez.walkerapi.Review.Model.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// DTO składa się z:
//     Imię, nazwisko sittera,
//     Zdjęcie sittera,
//     Zdjęcie dodane przez sittera
//     data spaceru,
//     data wystawienia opinii


@Document(collection = "dog_reviews")
@Getter
@Setter
@RequiredArgsConstructor
public class DogReview {
    @Id
    private String id;

    private String walkId;

    private String dogId;
    
    private String sitterId;

    private String dogPhoto;

    private String reviewBody;

    private LocalDateTime reviewDate;


    public DogReview(String walkId, String dogId, String sitterId, String dogPhoto, String reviewBody, LocalDateTime reviewDate) {
        this.walkId = walkId;
        this.dogId = dogId;
        this.sitterId = sitterId;
        this.dogPhoto = dogPhoto;
        this.reviewBody = reviewBody;
        this.reviewDate = reviewDate;
    }

}
