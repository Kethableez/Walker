package com.kethableez.walkerapi.Review.Model.DTO;

import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;

public class DogReviewCard {
    private UserInfo sitter;
    private DogInfo dog;
    private WalkInfo walk;
    private String photo;
}
