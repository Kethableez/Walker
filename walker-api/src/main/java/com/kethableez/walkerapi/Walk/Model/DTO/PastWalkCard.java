package com.kethableez.walkerapi.Walk.Model.DTO;

import java.time.LocalDateTime;

import com.kethableez.walkerapi.User.Model.DTO.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PastWalkCard {
    private String walkId;
    private Boolean isDogReviewed;
    private Boolean isSitterReviewed;
    private LocalDateTime walkDateTime;
    private String dogName;
    private String dogPhoto;
    private UserInfo sitterInfo;
}
