package com.kethableez.walkerapi.Request;

import java.time.LocalTime;

import com.kethableez.walkerapi.Model.Enum.DogType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DogRequest {
    private String name;
    private String dogBreed;
    private DogType dogType;
    @DateTimeFormat(pattern = "kk:mm:ss")
    private LocalTime walkDuration;
    private String characteristic;
    private String walkIntensity;
    private String walkDescription;
    private String dogPhoto;
}
