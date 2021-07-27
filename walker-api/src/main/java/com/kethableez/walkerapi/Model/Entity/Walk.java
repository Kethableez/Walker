package com.kethableez.walkerapi.Model.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "walks")
@Getter
@Setter
@RequiredArgsConstructor
public class Walk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "dog_id")
    @OneToOne(cascade = CascadeType.REMOVE)
    private Dog dog;

    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
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


    public Walk(Dog dog, LocalDateTime walkDateTime, String city, String address, Float walkLat, Float walkLon, String walkDescription, boolean isBooked) {
        this.dog = dog;
        this.walkDateTime = walkDateTime;
        this.city = city;
        this.address = address;
        this.walkLat = walkLat;
        this.walkLon = walkLon;
        this.walkDescription = walkDescription;
        this.isBooked = isBooked;
    }

}
