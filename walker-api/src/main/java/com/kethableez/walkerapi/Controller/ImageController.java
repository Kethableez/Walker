package com.kethableez.walkerapi.Controller;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.DogImage;
import com.kethableez.walkerapi.Model.Response.MessageResponse;
import com.kethableez.walkerapi.Service.ImageService;

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

    @PostMapping("/upload/{dogId}")
    public ResponseEntity<?> uploadDogPhoto(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable("dogId") String dogId) {
        try {
            imageService.uploadPhoto(imageFile, dogId);
            return ResponseEntity.ok(new MessageResponse("Dodano zdjęcie!"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
        }
    }

    @GetMapping(value="/{dogId}/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getImage(@PathVariable("dogId") String dogId, @PathVariable("filename") String filename) {
        try {
            Optional<DogImage> image = imageService.getImage(dogId, filename);
            if(image.isPresent()) return new ResponseEntity<>(image.get().getFile(), HttpStatus.OK);
            else return ResponseEntity.badRequest().body("Brak takiego zdjęcia!");
            
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error!");
        }
    }


    // @GetMapping(
    //         value = "/background",
    //         produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    // public ResponseEntity<?> getBackground() {
    //     try {
    //         byte[] background = this.imageService.getImage(ImageType.BACKGROUND).getFiledata();
    //         return new ResponseEntity<>(background, HttpStatus.OK);
    //     }
    //     catch (Exception e){
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body("Image cannot be load!");
    //     }
    // }

    // @GetMapping(
    //         value = "/logo",
    //         produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    // public ResponseEntity<?> getLogo() {
    //     try {
    //         byte[] background = this.imageService.getImage(ImageType.LOGO).getFiledata();
    //         return new ResponseEntity<>(background, HttpStatus.OK);
    //     }
    //     catch (Exception e){
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body("Image cannot be load!");
    //     }
    // }

    // @PostMapping("/admin/uploadBackground")
    // public ResponseEntity<?> uploadBackground(@RequestParam("imageFile") MultipartFile imageFile){
    //     try {
    //         this.imageService.saveImage(imageFile, ImageType.BACKGROUND);
    //         return ResponseEntity.ok("Dodano zdjęcie!");
    //     }
    //     catch (Exception e){
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body("Image cannot be upload!");
    //     }
    // }

    // @PostMapping("/admin/uploadLogo")
    // public ResponseEntity<?> uploadLogo(@RequestParam("imageFile") MultipartFile imageFile){
    //     try {
    //         this.imageService.saveImage(imageFile, ImageType.LOGO);
    //         return ResponseEntity.ok("Dodano zdjęcie!");
    //     }
    //     catch (Exception e){
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body("");
    //     }
    // }
}
