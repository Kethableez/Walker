package com.kethableez.walkerapi.Activity.Repository;

import com.kethableez.walkerapi.Activity.Model.Activity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    
}
