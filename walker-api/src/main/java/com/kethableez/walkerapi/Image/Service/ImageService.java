package com.kethableez.walkerapi.Image.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Image.Model.DogImage;
import com.kethableez.walkerapi.Image.Model.DogReviewImage;
import com.kethableez.walkerapi.Image.Model.UserImage;
import com.kethableez.walkerapi.Image.Repository.DogImageRepository;
import com.kethableez.walkerapi.Image.Repository.DogReviewImageRepository;
import com.kethableez.walkerapi.Image.Repository.UserImageRepository;
import com.kethableez.walkerapi.Review.Repository.DogReviewRepository;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static String UrlBase = "http://localhost:8080/image/review/%s/%s/%s";

    @Autowired
    private final DogImageRepository dogImageRepository;

    @Autowired
    private final UserImageRepository userImageRepository;

    @Autowired
    private final DogReviewImageRepository reviewImageRepository;

    @Autowired
    private final DogReviewRepository dogReviewRepository;

    public String uploadDogPhoto(MultipartFile file, String dogId) throws IOException {
        DogImage image = new DogImage(dogId, file.getOriginalFilename(), file.getBytes());
        dogImageRepository.save(image);
        return "OK";
    }

    public ActionResponse changeUserPhoto(MultipartFile file, String userId) throws IOException {
        userImageRepository.deleteById(userImageRepository.findByUserId(userId).get().getId());
        
        UserImage image = new UserImage(userId, file.getOriginalFilename(), file.getBytes());
        userImageRepository.save(image);
        return new ActionResponse(true, "Dodano zdjęcie");
    }

    public String uploadUserPhoto(MultipartFile file, String userId) throws IOException {
        UserImage image = new UserImage(userId, file.getOriginalFilename(), file.getBytes());
        userImageRepository.save(image);
        return "OK";
    }

    public ActionResponse uploadDogReviewPhoto(MultipartFile file, String sitterId, String reviewId) throws IOException {
        DogReviewImage image = new DogReviewImage(dogReviewRepository.findById(reviewId).get().getDogId(), sitterId,  reviewId, file.getOriginalFilename(), file.getBytes());
        reviewImageRepository.save(image);
        return new ActionResponse(true, "Dodano zdjęcie");
    }

    public Optional<DogImage> getDogImage(String dogId, String fileName) {
        return dogImageRepository.findByDogIdAndFileName(dogId, fileName);
    }

    public Optional<UserImage> getUserImage(String userId, String fileName) {
        return userImageRepository.findByUserIdAndFileName(userId, fileName);
    }

    public List<String> getSitterReviewImages(String sitterId) {
        List<String> imageUrls = new ArrayList<>();
        reviewImageRepository.findAllBySitterId(sitterId).forEach(i -> imageUrls.add(String.format(UrlBase, "u", i.getSitterId(), i.getFileName())));
        return imageUrls;
    }

    public Optional<DogReviewImage> getSitterReviewImage(String sitterId, String filename) {
        return reviewImageRepository.findBySitterIdAndFileName(sitterId, filename);
    }


    public List<String> getDogReviewImages(String dogId) {
        List<String> imageUrls = new ArrayList<>();
        reviewImageRepository.findAllByDogId(dogId).forEach(d -> imageUrls.add(String.format(UrlBase, "d", d.getDogId(), d.getFileName())));
        return imageUrls;
    }

    public Optional<DogReviewImage> getDogReviewImage(String dogId, String filename) {
        return reviewImageRepository.findByDogIdAndFileName(dogId, filename);
    }

    public Optional<DogReviewImage> getReviewImage(String reviewId) {
        return reviewImageRepository.findByReviewId(reviewId);
    }
}
