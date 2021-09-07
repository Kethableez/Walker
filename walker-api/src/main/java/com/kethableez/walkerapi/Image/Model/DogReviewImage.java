package com.kethableez.walkerapi.Image.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection="dog_review_images")
@Getter
@Setter
public class DogReviewImage {
    @Id
    private String id;

    private String dogId;
    
    private String sitterId;

    private String reviewId;

    private String fileName;

    private byte[] file;


    public DogReviewImage(String dogId, String sitterId, String reviewId, String fileName, byte[] file) {
        this.dogId = dogId;
        this.sitterId = sitterId;
        this.reviewId = reviewId;
        this.fileName = fileName;
        this.file = file;
    }

    public String getDogFilePath() {
        return String.format("http://localhost:8080/image/review/d/%s/%s", this.dogId, this.fileName);
    }

    public String getSitterFilePath() {
        return String.format("http://localhost:8080/image/review/u/%s/%s", this.sitterId, this.fileName);
    }

    public String getReviewFilePath() {
        return String.format("http://localhost:8080/image/review/r/%s", this.reviewId);
    }
}
