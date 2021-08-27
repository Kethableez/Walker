package com.kethableez.walkerapi.Sitter.Model;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Review.Controller.ReviewController;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sitter {
    private User user;
    private List<Dog> dogs;
    private List<Walk> walks;
    private Float rating;

    // private UserCard sitter;
    // private Float rating;
    // private List<DogInfo> favouriteDogs;
    // private List<Review> reviews;
    // private List<WalkInfo> incomingWalks;
    // private List<WalkInfo> pastWalks;
}
