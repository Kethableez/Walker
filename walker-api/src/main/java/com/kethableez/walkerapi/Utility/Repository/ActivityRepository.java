package com.kethableez.walkerapi.Utility.Repository;


import java.util.List;

import com.kethableez.walkerapi.Utility.Model.Activity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByOrderByTimestampAsc();
    
}
