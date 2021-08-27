package com.kethableez.walkerapi.Review.Repository;

import java.util.List;

import com.kethableez.walkerapi.Review.Model.Entity.SitterReview;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterReviewRepository extends MongoRepository<SitterReview, String>{
    List<SitterReview> findAllBySitterId(String sitterId);
}
