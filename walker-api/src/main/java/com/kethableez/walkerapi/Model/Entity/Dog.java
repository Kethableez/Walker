package com.kethableez.walkerapi.Model.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kethableez.walkerapi.Model.Enum.DogType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Document(collection = "dogs")
@Setter
@Getter
@RequiredArgsConstructor
public class Dog {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String ownerId;
    
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 35)
    private String dogBreed;

    @NotBlank
    private DogType dogType;

    @NotBlank
    private String walkDuration;

    @NotBlank
    @Size(max = 250)
    private String characteristic;

    @NotBlank
    private String walkIntensity;

    @NotBlank
    @Size(max = 250)
    private String walkDescription;

    @NotBlank
    private String dogPhoto;

    public Dog(String ownerId, String name, String dogBreed, DogType dogType, String walkDuration, String characteristic, String walkIntensity, String walkDescription, String dogPhoto) {
        this.ownerId = ownerId;
        this.name = name;
        this.dogBreed = dogBreed;
        this.dogType = dogType;
        this.walkDuration = walkDuration;
        this.characteristic = characteristic;
        this.walkIntensity = walkIntensity;
        this.walkDescription = walkDescription;
        this.dogPhoto = dogPhoto;
    }
    
    @Override
    public boolean equals(Object obj) {
        Dog w = (Dog) obj;
        return id.equals(w.getId());
    }
    
    public String getDogPhoto() {
        // http://localhost:8080/image/222333/beagle.jpg
        return "http://localhost:8080/image/" + this.id + "/" + this.dogPhoto;
    }
}
