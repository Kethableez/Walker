package com.kethableez.walkerapi.Controller;

import com.kethableez.walkerapi.Model.ImageType;
import com.kethableez.walkerapi.Repository.UtilityImageRepository;
import com.kethableez.walkerapi.Service.UtilityImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/utility_images")
@RequiredArgsConstructor
public class UtilityImageController {

    @Autowired
    private final UtilityImageService imageService;

    @GetMapping(
            value = "/background",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getBackground() {
        try {
            byte[] background = this.imageService.getImage(ImageType.BACKGROUND).getFiledata();
            return new ResponseEntity<>(background, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Image cannot be load!");
        }
    }

    @PostMapping("/admin/uploadBackground")
    public ResponseEntity<?> uploadBackground(@RequestParam("imageFile") MultipartFile imageFile){
        try {
            this.imageService.saveImage(imageFile, ImageType.BACKGROUND);
            return ResponseEntity.ok("Dodano zdjęcie!");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Image cannot be upload!");
        }
    }
}
