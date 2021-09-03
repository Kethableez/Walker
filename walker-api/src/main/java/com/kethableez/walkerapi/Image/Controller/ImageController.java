package com.kethableez.walkerapi.Image.Controller;

import java.io.IOException;
import java.util.Optional;

import com.kethableez.walkerapi.Image.Model.DogImage;
import com.kethableez.walkerapi.Image.Model.UserImage;
import com.kethableez.walkerapi.Image.Service.ImageService;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private final ImageService imageService;

    @PostMapping("/dog/upload/{dogId}")
    public ResponseEntity<?> uploadDogPhoto(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable("dogId") String dogId) {
        try {
            imageService.uploadDogPhoto(imageFile, dogId);
            return ResponseEntity.ok(new MessageResponse("Dodano zdjęcie!"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
        }
    }

    @PostMapping("/user/upload/{userId}")
    public ResponseEntity<?> uploadUserPhoto(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable("userId") String userId) {
        try {
            imageService.uploadUserPhoto(imageFile, userId);
            return ResponseEntity.ok(new MessageResponse("Dodano zdjęcie"));
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Wystąpił problem"));
        }
    }

    @GetMapping(value="/dog/{dogId}/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getDogImage(@PathVariable("dogId") String dogId, @PathVariable("filename") String filename) {
        try {
            Optional<DogImage> image = imageService.getDogImage(dogId, filename);
            if(image.isPresent()) return new ResponseEntity<>(image.get().getFile(), HttpStatus.OK);
            else return ResponseEntity.badRequest().body("Brak takiego zdjęcia!");
            
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error!");
        }
    }

    @GetMapping(value="/user/{userId}/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getUserImage(@PathVariable("userId") String userId, @PathVariable("filename") String filename) {
        try {
            Optional<UserImage> image = imageService.getUserImage(userId, filename);
            if (image.isPresent()) return new ResponseEntity<>(image.get().getFile(), HttpStatus.OK);
            else return ResponseEntity.badRequest().body("Brak takiego zdjęcia!");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error!");
        }
    }
}
