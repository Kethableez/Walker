package com.kethableez.walkerapi.Request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalkRequest {
    private Long dogId;
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime walkDateTime;
    private String city;
    private String address;
    private Float walkLat;
    private Float walkLon;
    private String walkDescription;
}