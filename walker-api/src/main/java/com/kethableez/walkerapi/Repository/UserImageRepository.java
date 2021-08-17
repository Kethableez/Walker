package com.kethableez.walkerapi.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.UserImage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserImageRepository extends MongoRepository<UserImage, String>{
    Optional<UserImage> findByUserIdAndFileName(String userId, String fileName);
}
