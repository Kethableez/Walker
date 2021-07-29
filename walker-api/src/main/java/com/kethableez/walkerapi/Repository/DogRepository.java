package com.kethableez.walkerapi.Repository;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends MongoRepository<Dog, String>{

    @Query("{'owner_id': '?0'}")
    List<Dog> findByOwnerId(String id);

}
