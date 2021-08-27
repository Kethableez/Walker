package com.kethableez.walkerapi.Utility.Mapper;

import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.Review.Repository.DogReviewRepository;
import com.kethableez.walkerapi.Review.Repository.SitterReviewRepository;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Walk.Model.DTO.PastWalkInfo;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
        UserCard userCard = new UserCard(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getBirthdate(),
            user.getAvatar(),
            user.getGender(),
            user.getDescription()
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


}
