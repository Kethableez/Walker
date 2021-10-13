package com.kethableez.walkerapi.Walk.Model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class WalkAdminInfo {
    private String id;
    private String ownerAvatar;
    private String ownerName;
    private String dogName;
    private boolean isBooked;
    private LocalDateTime walkDate;
    private String city;
}
