package com.kethableez.walkerapi.Service;

import java.io.IOException;
import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.DogImage;
import com.kethableez.walkerapi.Repository.DogImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
    
    @Autowired
    private final DogImageRepository dogImageRepository;

    public String uploadPhoto(MultipartFile file, String dogId) throws IOException {
        DogImage image = new DogImage(dogId,
         file.getOriginalFilename(),
        file.getBytes()
        );

         dogImageRepository.save(image);
         return "OK";
    }

    public Optional<DogImage> getImage(String dogId, String fileName) {
        return dogImageRepository.findByDogIdAndFileName(dogId, fileName);
    }
}
