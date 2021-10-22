package com.kethableez.walkerapi.Utility.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "activity")
@Getter
@Setter
public class Activity {
    @Id
    private String id;
    private ActivityType type;
    private String userId;
    private LocalDateTime timestamp;

    public Activity(ActivityType type, String userId, LocalDateTime timestamp) {
        this.type = type;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}
