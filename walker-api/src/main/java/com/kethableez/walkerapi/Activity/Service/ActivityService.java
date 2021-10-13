package com.kethableez.walkerapi.Activity.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Activity.Model.Activity;
import com.kethableez.walkerapi.Activity.Model.ActivityType;
import com.kethableez.walkerapi.Activity.Repository.ActivityRepository;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    public ActivityRepository activityRepository;
    
    @Autowired
    public MapperService mapperService;

    public void recordActivity(String userId, String additionalId, ActivityType activityType) {
        Activity activity = new Activity(userId, additionalId, activityType, LocalDateTime.now());
        activityRepository.save(activity);
    }

    public List<Activity> getUserActivity() {
        return activityRepository.findAll();
    }
}
