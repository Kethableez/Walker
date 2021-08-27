package com.kethableez.walkerapi.Dog.Model.Request;

import com.kethableez.walkerapi.Utility.Enum.DogType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DogRequest {
    private String name;
    private String dogBreed;
    private DogType dogType;
    private String walkDuration;
    private String characteristic;
    private String walkIntensity;
    private String walkDescription;
    private String dogPhoto;
}
