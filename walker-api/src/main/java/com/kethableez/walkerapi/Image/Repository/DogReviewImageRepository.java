package com.kethableez.walkerapi.Image.Repository;

import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Image.Model.DogReviewImage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogReviewImageRepository extends MongoRepository<DogReviewImage, String>{
    List<DogReviewImage> findAllByDogId(String dogId);
    List<DogReviewImage> findAllBySitterId(String sitterId);
    Optional<DogReviewImage> findBySitterIdAndFileName(String sitterId, String filename);
    Optional<DogReviewImage> findByDogIdAndFileName(String dogId, String filename);
    Optional<DogReviewImage> findByReviewId(String reviewId);
}
