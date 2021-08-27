package com.kethableez.walkerapi.Owner.Model;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Review.Model.DTO.DogReviewCard;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;

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

    // private UserCard owner;
    // private List<DogInfo> ownerDogs;
    // private List<WalkInfo> plannedWalks;
    // private List<WalkInfo> pastWalks;
}
