package com.kethableez.walkerapi.Service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilityImageService {

    // @Autowired
    // private final UtilityImageRepository imageRepository;

    // public UtilityImage saveImage(MultipartFile file, ImageType imageType) throws IOException {
    //     UtilityImage image = new UtilityImage(
    //             file.getOriginalFilename(),
    //             file.getContentType(),
    //             imageType,
    //             file.getBytes()
    //     );

    //     return this.imageRepository.save(image);
    // }

    // public UtilityImage getImage(String filename) {
    //     return this.imageRepository.findByFilename(filename);
    // }

    // public UtilityImage getImage(ImageType type) {
    //     return this.imageRepository.findByImagetype(type);
    // }
}
