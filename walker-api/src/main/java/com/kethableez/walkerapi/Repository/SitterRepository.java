package com.kethableez.walkerapi.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.Sitter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterRepository extends MongoRepository<Sitter, String> {
    @Query("{'user.username': '?0'}")
    Optional<Sitter> findByUsername(String username);
}
