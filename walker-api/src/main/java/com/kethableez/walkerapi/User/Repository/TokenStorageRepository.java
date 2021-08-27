package com.kethableez.walkerapi.User.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.User.Model.Entity.TokenStorage;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TokenStorageRepository extends MongoRepository<TokenStorage, String> {
    Optional<TokenStorage> findByToken(String token);
}