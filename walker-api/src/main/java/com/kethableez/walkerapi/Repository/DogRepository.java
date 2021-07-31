package com.kethableez.walkerapi.Repository;

import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.Dog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends MongoRepository<Dog, String>{

    @Query("{ownerId: '?0'}")
    List<Dog> findByOwnerId(String id);

    @Query("{$and: [{_id: ObjectId('?0')}, {ownerId: '?1'}]}")
    Optional<Dog> findByDogIdAndOwnerId(String dogId, String ownerId);
}
