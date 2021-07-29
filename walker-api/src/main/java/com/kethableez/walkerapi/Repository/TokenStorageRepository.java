package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.TokenStorage;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TokenStorageRepository extends MongoRepository<TokenStorage, String> {
    TokenStorage findByToken(String token);
}