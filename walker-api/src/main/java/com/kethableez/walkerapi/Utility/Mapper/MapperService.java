package com.kethableez.walkerapi.Utility.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.Owner.Model.Owner;
import com.kethableez.walkerapi.Review.Model.DTO.DogReviewCard;
import com.kethableez.walkerapi.Review.Model.DTO.SitterReviewCard;
import com.kethableez.walkerapi.Review.Model.Entity.DogReview;
import com.kethableez.walkerapi.Review.Model.Entity.SitterReview;
import com.kethableez.walkerapi.Review.Repository.DogReviewRepository;
import com.kethableez.walkerapi.Review.Repository.SitterReviewRepository;
import com.kethableez.walkerapi.Sitter.Model.Sitter;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Walk.Model.DTO.PastWalkInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final static String PATTERN = "%s %s";

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DogReviewRepository dogReviewRepository;

    @Autowired
    private final SitterReviewRepository sitterReviewRepository;

    public UserInfo userInfoMapper(String userId) {
        User user = userRepository.findById(userId).get();
        UserInfo userInfo = new UserInfo(
           user.getId(),
           user.getUsername(),
           user.getFirstName(),
           user.getLastName(),
           user.getAvatar(),
           user.getDescription()
        );
        return userInfo;
    }

    public UserCard userCardMapper(String userId) {
        User user = userRepository.findById(userId).get();
        List<Role> roles = new ArrayList<>();
        
        user.getRoles().stream().forEach(r -> roles.add(r.getRole()));

        UserCard userCard = new UserCard(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getBirthdate(),
            user.getCity(),
            user.getAvatar(),
            user.getGender(),
            user.getDescription(),
            roles
        );

        return userCard;
    }

    public DogInfo dogInfoMapper(String dogId) {
        Dog dog = dogRepository.findById(dogId).get();
        DogInfo dogInfo = new DogInfo(dog.getId(), dog.getName(), dog.getDogPhoto());
        return dogInfo;
    }

    public WalkInfo walkInfoMapper(String walkId) {
        Walk walk = walkRepository.findById(walkId).get();
        Dog dog = dogRepository.findById(walk.getDogId()).get();
        
        WalkInfo walkInfo = new WalkInfo(
            walk.getId(), 
            dog.getName(),
            dog.getDogPhoto(),
            walk.getCity(),
            walk.getAddress(),
            walk.getWalkDateTime(),
            walk.isBooked(),
            walk.getSitterId()
        );

        return walkInfo;
    }

    public PastWalkInfo pastWalkInfoMapper(String walkId) {
        Walk walk = walkRepository.findById(walkId).get();
        Dog dog = dogRepository.findById(walk.getDogId()).get();
        User sitter = userRepository.findById(walk.getSitterId()).get();

        PastWalkInfo pastWalkInfo = new PastWalkInfo(
            walk.getId(), 
            dog.getName(),
            dog.getDogPhoto(),
            walk.getCity(),
            walk.getAddress(),
            walk.getWalkDateTime(),
            String.format(PATTERN, sitter.getFirstName(), sitter.getLastName()),
            sitter.getAvatar(),
            walk.isSitterReviewed(),
            walk.isDogReviewed()
        );

        return pastWalkInfo;
    }


    public DogReviewCard dogReviewMapper(String reviewId) {
        DogReview dogReview = dogReviewRepository.findById(reviewId).get();
        UserInfo userInfo = userInfoMapper(dogReview.getSitterId());
        DogInfo dogInfo = dogInfoMapper(dogReview.getDogId());
        WalkInfo walkInfo = walkInfoMapper(dogReview.getWalkId());
        DogReviewCard review = new DogReviewCard(dogReview.getId(), userInfo, dogInfo, walkInfo, dogReview.getDogPhoto(), dogReview.getReviewBody(), dogReview.getReviewDate());
        
        return review;
    }

    public SitterReviewCard sitterReviewMapper(String reviewId) {
        SitterReview sitterReview = sitterReviewRepository.findById(reviewId).get();
        UserInfo userInfo = userInfoMapper(sitterReview.getOwnerId());
        DogInfo dogInfo = dogInfoMapper(sitterReview.getDogId());
        WalkInfo walkInfo = walkInfoMapper(sitterReview.getWalkId());
        SitterReviewCard review = new SitterReviewCard(sitterReview.getId(), userInfo, dogInfo, walkInfo, sitterReview.getRating(), sitterReview.getReviewBody(), sitterReview.getReviewDate());

        return review;
    }

    public DogCard dogCardMapper(String dogId) {
        Dog dog = dogRepository.findById(dogId).get();
        UserInfo owner = userInfoMapper(dog.getOwnerId());
        List<DogReviewCard> reviews = new ArrayList<>();
        dogReviewRepository.findAllByDogId(dogId).stream().forEach(r -> reviews.add(dogReviewMapper(r.getId())));

        List<WalkInfo> incomingWalks = new ArrayList<>();
        Pageable page = PageRequest.of(0, 5);

        walkRepository.findByWalkDateTimeGreaterThanAndDogIdOrderByWalkDateTimeAsc(
            LocalDateTime.now(), 
            dogId,
            page).stream().forEach(r -> incomingWalks.add(walkInfoMapper(r.getId())));

        List<String> images = new ArrayList<>();

        DogCard dogCard = new DogCard(dog, owner, reviews, incomingWalks, images);
        return dogCard;
    }

    public Sitter sitterMapper(String sitterId) {
        UserCard sitterCard = userCardMapper(sitterId);
        List<SitterReviewCard> reviews = new ArrayList<>();
        sitterReviewRepository.findAllBySitterId(sitterId).stream().forEach(review -> reviews.add(sitterReviewMapper(review.getId())));
        Float rating = (float)reviews.stream().mapToDouble(r -> r.getRating()).sum();

        Sitter sitter = new Sitter(sitterCard, rating, reviews);

        return sitter;
    }

    public Owner ownerMapper(String ownerId) {
        UserCard ownerCard = userCardMapper(ownerId);
        List<DogInfo> dogs = new ArrayList<>();
        dogRepository.findByOwnerId(ownerId).stream().forEach(dog -> dogs.add(dogInfoMapper(dog.getId())));

        Owner owner = new Owner(ownerCard, dogs);
        return owner;
    }

}
