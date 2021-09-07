package com.kethableez.walkerapi.Notification;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NotificationDTO {
    private String action;
    private String text;
    private List<String> uIds;
}
