package com.kethableez.walkerapi.Model.Entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kethableez.walkerapi.Model.Enum.DogType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dogs")
@Setter
@Getter
@RequiredArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    @Size(max = 20)
    private String owner;
    
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 35)
    private String dogBreed;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private DogType dogType;

    @NotBlank
    @DateTimeFormat(pattern = "kk:mm:ss")
    private LocalTime walkDuration;

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

    public Dog(String owner, String name, String dogBreed, DogType dogType, LocalTime walkDuration, String characteristic, String walkIntensity, String walkDescription, String dogPhoto) {
        this.owner = owner;
        this.name = name;
        this.dogBreed = dogBreed;
        this.dogType = dogType;
        this.walkDuration = walkDuration;
        this.characteristic = characteristic;
        this.walkIntensity = walkIntensity;
        this.walkDescription = walkDescription;
        this.dogPhoto = dogPhoto;
    }
    

}
