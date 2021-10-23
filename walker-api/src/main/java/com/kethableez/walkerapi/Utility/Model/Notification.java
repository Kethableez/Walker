package com.kethableez.walkerapi.Utility.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "notification")
@Getter
@Setter
public class Notification {
    @Id
    private String id;
    private String senderId;
    private String senderName;
    private String recieverId;
    private String additionalId;
    private NotificationType type;
    private LocalDateTime timestamp;
    private Boolean isRead;


    public Notification(String senderId, String senderName, String recieverId, String additionalId, NotificationType type, LocalDateTime timestamp, Boolean isRead) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.recieverId = recieverId;
        this.additionalId = additionalId;
        this.type = type;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

}
