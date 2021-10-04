package com.kethableez.walkerapi.Notification.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "notifications")
public class Notification {
    
    @Id
    private String id;

    private String senderId;

    private String recieverId;

    private Action action;

    private LocalDateTime timestamp;

    private boolean isRead;


    public Notification(String senderId, String recieverId, Action action, LocalDateTime timestamp, boolean isRead) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.action = action;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

}
