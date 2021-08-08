package com.kethableez.walkerapi.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.DogImage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogImageRepository extends MongoRepository<DogImage, String>{
    Optional<DogImage> findByDogIdAndFileName(String dogId, String fileName);
    
}
