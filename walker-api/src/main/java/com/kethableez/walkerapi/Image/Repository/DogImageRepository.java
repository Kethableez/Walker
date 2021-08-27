package com.kethableez.walkerapi.Image.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Image.Model.DogImage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogImageRepository extends MongoRepository<DogImage, String>{
    Optional<DogImage> findByDogIdAndFileName(String dogId, String fileName);
    
}
