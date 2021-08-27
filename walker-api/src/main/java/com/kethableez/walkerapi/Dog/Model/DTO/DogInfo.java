package com.kethableez.walkerapi.Dog.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DogInfo {
    private String id;
    private String dogName;
    private String dogPhoto;
}
