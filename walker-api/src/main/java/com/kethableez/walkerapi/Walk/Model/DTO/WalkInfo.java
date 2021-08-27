package com.kethableez.walkerapi.Walk.Model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class WalkInfo {
    private String id;
    private String dogName;
    private String dogPhoto;
    private String city;
    private String adress;
    private LocalDateTime walkDateTime;
    private Boolean isBooked;
    private String sitterId;
}
