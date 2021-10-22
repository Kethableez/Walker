package com.kethableez.walkerapi.Review.Model.DTO;

import java.time.LocalDateTime;

import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SitterReviewCard {
    private String id;
    private UserInfo owner;
    private WalkCard walkCard;
    private Float rating;
    private String reviewBody;
    private LocalDateTime reviewDate;
}
