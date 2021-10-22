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
public class DogReviewCard {
    private String id;
    private UserInfo sitter;
    private WalkCard walkCard;
    private String photo;
    private String reviewBody;
    private LocalDateTime reviewDate;
}
