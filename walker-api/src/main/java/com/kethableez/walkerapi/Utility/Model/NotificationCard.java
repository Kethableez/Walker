package com.kethableez.walkerapi.Utility.Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationCard {
    private String senderId;
    private String senderName;
    private String additionalId;
    private NotificationType type;
    private LocalDateTime timestamp;
    private Boolean isRead;
}
