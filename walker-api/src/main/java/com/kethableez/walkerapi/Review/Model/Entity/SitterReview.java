package com.kethableez.walkerapi.Review.Model.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// DTO składa się z:
//     Imię, nazwisko ownera,
//     Zdjęcie ownera,
//     data spaceru,
//     data wystawienia opinii
//     Rating + body
//     Z jakim psem

// TODO: Dodać mapperService do transferu encji
// TODO: Po logowaniu zaciągać tylko podstawowe dane użytkownika
// TODO: Po wejściu na profil jakiegoś uzytkownika brać podstawowe dane o nim (User, Owner, Sitter, Admin)
// TODO: Po wejściu na swój profil dociągać dane takie jak rating, dogs, walks, galeria etc.
// TODO: Przerobić WalkCard, DogCard i dodać do mapperService
// TODO: Sessionstorage tylko RegularUser

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

    private String ownerName;
    
    private String dogName;

    private Float rating;

    private String reviewBody;

    private LocalDateTime reviewDate;


    public SitterReview(String walkId, String sitterId, String ownerId, String ownerName, String dogName, Float rating, String reviewBody, LocalDateTime reviewDate) {
        this.walkId = walkId;
        this.sitterId = sitterId;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.dogName = dogName;
        this.rating = rating;
        this.reviewBody = reviewBody;
        this.reviewDate = reviewDate;
    }

}
