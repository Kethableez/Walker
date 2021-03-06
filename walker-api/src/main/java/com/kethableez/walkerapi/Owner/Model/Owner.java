package com.kethableez.walkerapi.Owner.Model;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Owner {
    private UserCard user;
    private List<DogCard> dogs;
    private List<String> dogImages;
}
