package com.kethableez.walkerapi.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.Owner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String>{

    @Query("{'user.username': '?0'}")
    Optional<Owner> findByUsername(String username);
}