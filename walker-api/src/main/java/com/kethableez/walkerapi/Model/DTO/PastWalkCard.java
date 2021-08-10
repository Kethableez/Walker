package com.kethableez.walkerapi.Model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PastWalkCard {
    private String walkId;
    private LocalDateTime walkDateTime;
    private String dogName;
    private String dogPhoto;
    private UserInfo sitterInfo;
}
