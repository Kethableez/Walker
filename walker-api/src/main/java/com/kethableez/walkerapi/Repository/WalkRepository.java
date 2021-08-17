package com.kethableez.walkerapi.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.google.common.base.Optional;
import com.kethableez.walkerapi.Model.Entity.Walk;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends MongoRepository<Walk, String>{

    List<Walk> findByOwnerId(String ownerId);

    List<Walk> findByDogId(String dogId);

    List<Walk> findBySitterId(String sitterId);

    @Query("{$and: [{_id: ObjectId('?0')}, {ownerId: '?1'}]}")
    Optional<Walk> findByWalkIdAndOwnerId(String walkId, String ownerId);

    @Query("{$and: [{_id: ObjectId('?0')}, {sitterId: '?1'}]}")
    Optional<Walk> findByWalkIdAndSitterId(String walkId, String sitterId);

    List<Walk> findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime currentTime, Boolean val);

    List<Walk> findByWalkDateTimeLessThanAndSitterId(LocalDateTime currentTime, String sitterId);

    List<Walk> findByWalkDateTimeLessThanAndOwnerIdAndIsBooked(LocalDateTime currentTime, String ownerId, Boolean value);

    List<Walk> findByWalkDateTimeGreaterThanAndSitterId(LocalDateTime currentTime, String sitterId);

    List<Walk> findByWalkDateTimeGreaterThanAndDogIdOrderByWalkDateTimeAsc(LocalDateTime currentTime, String dogId, Pageable page);
}
