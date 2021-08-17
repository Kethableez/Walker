package com.kethableez.walkerapi.Service;

import java.io.IOException;
import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.DogImage;
import com.kethableez.walkerapi.Model.Entity.UserImage;
import com.kethableez.walkerapi.Model.Response.ActionResponse;
import com.kethableez.walkerapi.Repository.DogImageRepository;
import com.kethableez.walkerapi.Repository.UserImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final DogImageRepository dogImageRepository;

    @Autowired
    private final UserImageRepository userImageRepository;

    public String uploadDogPhoto(MultipartFile file, String dogId) throws IOException {
        DogImage image = new DogImage(dogId, file.getOriginalFilename(), file.getBytes());
        dogImageRepository.save(image);
        return "OK";
    }

    public ActionResponse uploadUPhoto(MultipartFile file, String userId) throws IOException {
        UserImage image = new UserImage(userId, file.getOriginalFilename(), file.getBytes());
        userImageRepository.save(image);
        return new ActionResponse(true, "Dodano zdjęcie");
    }

    public String uploadUserPhoto(MultipartFile file, String userId) throws IOException {
        UserImage image = new UserImage(userId, file.getOriginalFilename(), file.getBytes());
        userImageRepository.save(image);
        return "OK";
    }

    public Optional<DogImage> getDogImage(String dogId, String fileName) {
        return dogImageRepository.findByDogIdAndFileName(dogId, fileName);
    }

    public Optional<UserImage> getUserImage(String userId, String fileName) {
        return userImageRepository.findByUserIdAndFileName(userId, fileName);
    }
}
