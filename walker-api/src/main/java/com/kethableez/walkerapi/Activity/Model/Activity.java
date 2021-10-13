package com.kethableez.walkerapi.Activity.Model;

import java.time.LocalDateTime;

import com.google.common.base.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "activity")
public class Activity {
    @Id
    private String id;

    private String userId;

    private String additionalId;

    private ActivityType activityType;

    private LocalDateTime activityTimestamp;


    public Activity(String userId, String additionalId, ActivityType activityType, LocalDateTime activityTimestamp) {
        this.userId = userId;
        this.additionalId = additionalId;
        this.activityType = activityType;
        this.activityTimestamp = activityTimestamp;
    }
    
}
