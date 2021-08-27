package com.kethableez.walkerapi.Review.Repository;

import java.util.List;

import com.kethableez.walkerapi.Review.Model.Entity.DogReview;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DogReviewRepository extends MongoRepository<DogReview, String>{
    List<DogReview> findAllByDogId(String dogId);
}
