package com.kethableez.walkerapi.Dog.Model.DTO;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Review.Model.DTO.DogReviewCard;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DogCard {
    private Dog dog;
    private UserInfo owner;
    private List<DogReviewCard> reviews;
    private List<WalkInfo> incomingWalks;
    private List<String> images;
}
