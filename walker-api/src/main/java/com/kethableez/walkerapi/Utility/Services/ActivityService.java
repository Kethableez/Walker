package com.kethableez.walkerapi.Utility.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Utility.Model.ActivityType;
import com.kethableez.walkerapi.Utility.Repository.ActivityRepository;
import com.kethableez.walkerapi.Utility.Model.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void reportActivity(String userId, ActivityType type) {
        Activity activity = new Activity(type, userId, LocalDateTime.now());
        activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findByOrderByTimestampAsc();
    }
}
