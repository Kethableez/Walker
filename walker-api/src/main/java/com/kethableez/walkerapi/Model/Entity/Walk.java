package com.kethableez.walkerapi.Model.Entity;

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

    private Dog dog;

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

    private String sitterId;

    public Walk(Dog dog, LocalDateTime walkDateTime, String city, String address, Float walkLat, Float walkLon,
            String walkDescription, boolean isBooked) {
        this.dog = dog;
        this.walkDateTime = walkDateTime;
        this.city = city;
        this.address = address;
        this.walkLat = walkLat;
        this.walkLon = walkLon;
        this.walkDescription = walkDescription;
        this.isBooked = isBooked;
    }

    @Override
    public boolean equals(Object obj) {
        Walk w = (Walk) obj;
        return id.equals(w.getId());
    }

}
