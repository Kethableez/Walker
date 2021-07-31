package com.kethableez.walkerapi.Model.DTO;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.Walk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class WalkCard {
    private Walk walk;
    private Dog dog;
    private UserInfo userInfo;
}
