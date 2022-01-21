package com.kethableez.walkerapi.Utility.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.Image.Repository.DogReviewImageRepository;
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
import com.kethableez.walkerapi.User.Model.DTO.UserWithAdditionalInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
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

    private static String UrlBase = "http://localhost:8080/image/review/%s/%s/%s";

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

    @Autowired
    private final DogReviewImageRepository reviewImageRepository;

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

        UserCard userCard = new UserCard(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getBirthdate(),
            user.getZipCode(),
            user.getCity(),
            user.getDistrictCode(),
            user.getRegionCode(),
            user.getAvatar(),
            user.getGender(),
            user.getDescription(),
            user.getRoles()
        );

        return userCard;
    }

    public WalkCard walkCardMapper(String walkId) {
        Walk walk = walkRepository.findById(walkId).get();
        Dog dog = dogRepository.findById(walk.getDogId()).get();
        UserCard owner = userCardMapper(walk.getOwnerId());
        if (walk.isBooked() && !walk.getSitterId().isEmpty()) {
            UserCard sitter = userCardMapper(walk.getSitterId());
            return new WalkCard(walk, dog, owner, sitter);
        }
        else return new WalkCard(walk, dog, owner);
    }


    public DogReviewCard dogReviewMapper(String reviewId) {
        DogReview dogReview = dogReviewRepository.findById(reviewId).get();
        UserInfo userInfo = userInfoMapper(dogReview.getSitterId());
        WalkCard walkCard = walkCardMapper(dogReview.getWalkId());
        DogReviewCard review = new DogReviewCard(dogReview.getId(), userInfo, walkCard, dogReview.getDogPhoto(), dogReview.getReviewBody(), dogReview.getReviewDate());
        
        return review;
    }

    public SitterReviewCard sitterReviewMapper(String reviewId) {
        SitterReview sitterReview = sitterReviewRepository.findById(reviewId).get();
        UserInfo userInfo = userInfoMapper(sitterReview.getOwnerId());
        WalkCard walkCard = walkCardMapper(sitterReview.getWalkId());
        SitterReviewCard review = new SitterReviewCard(sitterReview.getId(), userInfo, walkCard, sitterReview.getRating(), sitterReview.getReviewBody(), sitterReview.getReviewDate());

        return review;
    }

    public DogCard dogCardMapper(String dogId) {
        Dog dog = dogRepository.findById(dogId).get();
        UserInfo owner = userInfoMapper(dog.getOwnerId());
        List<DogReviewCard> reviews = dogReviewRepository.findAllByDogId(dogId).stream().map(review -> dogReviewMapper(review.getId())).collect(Collectors.toList());

        Pageable page = PageRequest.of(0, 5);
        List<WalkCard> incomingWalks = walkRepository.findByWalkDateTimeGreaterThanAndDogIdOrderByWalkDateTimeAsc(
            LocalDateTime.now(), 
            dogId,
            page).stream().map(walk -> walkCardMapper(walk.getId())).collect(Collectors.toList());

        List<String> images = new ArrayList<>();
        reviewImageRepository.findAllByDogId(dogId).stream().forEach(i -> images.add(i.getDogFilePath()));

        DogCard dogCard = new DogCard(dog, owner, reviews, incomingWalks, images);
        return dogCard;
    }

    public Sitter sitterMapper(String sitterId) {
        UserCard sitterCard = userCardMapper(sitterId);
        List<SitterReviewCard> reviews = new ArrayList<>();
        sitterReviewRepository.findAllBySitterId(sitterId).stream().forEach(review -> reviews.add(sitterReviewMapper(review.getId())));
        Float rating = (float)reviews.stream().mapToDouble(r -> r.getRating()).sum() / reviews.size();
        List<String> images = new ArrayList<>();

        reviewImageRepository.findAllBySitterId(sitterId).forEach(i -> images.add(String.format(UrlBase, "u", i.getSitterId(), i.getFileName())));

        Sitter sitter = new Sitter(sitterCard, rating, reviews, images);

        return sitter;
    }

    public Owner ownerMapper(String ownerId) {
        UserCard ownerCard = userCardMapper(ownerId);
        List<DogCard> dogs = dogRepository.findByOwnerId(ownerId).stream().map(dog -> dogCardMapper(dog.getId())).collect(Collectors.toList());

        List<String> images = dogs.stream()
        .map(dog -> dog.getDog().getId())
        .map(dogId -> reviewImageRepository.findAllByDogId(dogId))
        .flatMap(Collection::stream)
        .map(reviewImage -> String.format(UrlBase, "d", reviewImage.getDogId(), reviewImage.getFileName()))
        .collect(Collectors.toList());

        Owner owner = new Owner(ownerCard, dogs, images);
        return owner;
    }

    public UserWithAdditionalInfo userWithAdditionalInfoMapper(String userId) {
        User user = userRepository.findById(userId).get();
        System.out.println(user);
        return new UserWithAdditionalInfo(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getAvatar(),
            this.getMainRole(user.getRoles()),
            user.getIsActive(),
            user.getIsBlocked(),
            user.getIsBanned()
        );
    }

    public Role getMainRole(Set<Role> userRoles) {
        Optional<Role> mainRole = userRoles.stream()
        .filter(role -> role != Role.ROLE_USER)
        .findFirst();

        return mainRole.isPresent() ? mainRole.get() : Role.ROLE_USER;
    }
}
