package com.kethableez.walkerapi.Walk.Model.DTO;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WalkWithDog {
    private Walk walk;
    private Dog dog;
}
