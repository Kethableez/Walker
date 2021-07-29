package com.kethableez.walkerapi.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Walk;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends MongoRepository<Walk, String>{

    @Query("{'dog.owner_id': '?0'}")
    List<Walk> findByOwnerId(String ownerId);

    @Query("{'dog._id': ObjectId('?0')}")
    List<Walk> findBydogId(String dogId);

    List<Walk> findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime currentTime, Boolean val);

    List<Walk> findByWalkDateTimeLessThanAndSitterId(LocalDateTime currentTime, String sitterId);
}
