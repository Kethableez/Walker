package com.kethableez.walkerapi.Walk.Model.Entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "walks")
@Getter
@Setter
@RequiredArgsConstructor
public class Walk {

    @Id
    private String id;

    private String dogId;

    private String ownerId;
    
    private String sitterId;
    
    private LocalDateTime walkDateTime;
    
    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private Float walkLat;

    @NotBlank
    private Float walkLon;

    @NotBlank
    private String walkDescription;

    @NotBlank
    private boolean isBooked;

    private boolean isSitterReviewed;

    private boolean isDogReviewed;

    public Walk(String dogId, String ownerId, LocalDateTime walkDateTime, String city, String address, Float walkLat, Float walkLon,
            String walkDescription, boolean isBooked, boolean isSitterReviewed, boolean isDogReviewed) {
        this.dogId = dogId;
        this.ownerId = ownerId;
        this.walkDateTime = walkDateTime;
        this.city = city;
        this.address = address;
        this.walkLat = walkLat;
        this.walkLon = walkLon;
        this.walkDescription = walkDescription;
        this.isBooked = isBooked;
        this.isSitterReviewed = isSitterReviewed;
        this.isDogReviewed = isDogReviewed;
    }

    @Override
    public boolean equals(Object obj) {
        Walk w = (Walk) obj;
        return id.equals(w.getId());
    }

}
