package com.kethableez.walkerapi.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/utility_images")
@RequiredArgsConstructor
public class UtilityImageController {

    // @Autowired
    // private final UtilityImageService imageService;

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
