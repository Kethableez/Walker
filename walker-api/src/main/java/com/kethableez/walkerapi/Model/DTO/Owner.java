package com.kethableez.walkerapi.Model.DTO;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Owner {
    private User user;
    private List<Dog> dogs;
    private List<Walk> walks;
}
