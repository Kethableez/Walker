package com.kethableez.walkerapi.Review.Model.DTO;

import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;

public class SitterReviewCard {
    private UserInfo owner;
    private DogInfo dog;
    private WalkInfo walk;
    private Float rating;
}
