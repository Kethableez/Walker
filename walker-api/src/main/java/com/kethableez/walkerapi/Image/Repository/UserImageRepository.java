package com.kethableez.walkerapi.Image.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Image.Model.UserImage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserImageRepository extends MongoRepository<UserImage, String>{
    Optional<UserImage> findByUserIdAndFileName(String userId, String fileName);
    Optional<UserImage> findByUserId(String userId);
}
