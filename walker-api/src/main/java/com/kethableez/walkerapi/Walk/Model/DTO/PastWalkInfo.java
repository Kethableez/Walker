package com.kethableez.walkerapi.Walk.Model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PastWalkInfo {
    private String id;
    private String dogName;
    private String dogPhoto;
    private String city;
    private String adress;
    private LocalDateTime walkDateTime;
    private String sitterName;
    private String sitterAvatar;
    private Boolean isSitterReviewed;
    private Boolean isDogReviewed;
}
