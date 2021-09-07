package com.kethableez.walkerapi.Review.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.Review.Model.DTO.DogReviewCard;
import com.kethableez.walkerapi.Review.Model.DTO.SitterReviewCard;
import com.kethableez.walkerapi.Review.Model.Entity.DogReview;
import com.kethableez.walkerapi.Review.Model.Entity.SitterReview;
import com.kethableez.walkerapi.Review.Model.Request.DogReviewRequest;
import com.kethableez.walkerapi.Review.Model.Request.SitterReviewRequest;
import com.kethableez.walkerapi.Review.Repository.DogReviewRepository;
import com.kethableez.walkerapi.Review.Repository.SitterReviewRepository;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    @Autowired
    private final SitterReviewRepository sitterReviewRepository;

    @Autowired
    private final DogReviewRepository dogReviewRepository;

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final MapperService mapper;

    public ActionResponse addSitterReview(String ownerId, SitterReviewRequest request) {
        if(checkDataCorrectness(ownerId, request.getWalkId())) {
            Walk walk = walkRepository.findById(request.getWalkId()).get();

            SitterReview review = new SitterReview(
                walk.getSitterId(),
                walk.getId(),
                walk.getOwnerId(),
                walk.getDogId(),
                request.getRating(),
                request.getReivewBody(),
                LocalDateTime.now()
            );
            walk.setSitterReviewed(true);
    
            sitterReviewRepository.save(review);
            walkRepository.save(walk);
            return new ActionResponse(true, "Dodano ocenę opiekuna");
        }
        else return new ActionResponse(false, "Niepoprawne dane");
    }

    public ActionResponse addDogReview(String sitterId, DogReviewRequest request) {

        if(checkDogReviewDataCorrectness(sitterId, request.getWalkId())) {
            Walk walk = walkRepository.findById(request.getWalkId()).get();

            DogReview review = new DogReview(
                walk.getId(),
                walk.getDogId(),
                walk.getSitterId(),
                request.getDogPhoto(),
                request.getReviewBody(),
                LocalDateTime.now()
            );
    
            walk.setDogReviewed(true);
    
            dogReviewRepository.save(review);
            walkRepository.save(walk);
            return new ActionResponse(true, review.getId());
        }
        else return new ActionResponse(false, "Niepoprawne dane");
    }

    public List<DogReview> getDogReview(String dogId) {
        return dogReviewRepository.findAllByDogId(dogId);
    }

    public List<DogReviewCard> getDogReviewCard(String dogId) {
        List<DogReviewCard> reviews = new ArrayList<>();
        getDogReview(dogId).stream().forEach(review -> reviews.add(mapper.dogReviewMapper(review.getId())));
        return reviews;
    }

    public List<SitterReview> getSitterReview(String sitterId) {
        return sitterReviewRepository.findAllBySitterId(sitterId);
    }

    public List<SitterReviewCard> getSitterReviewCard(String sitterId) {
        List<SitterReviewCard> reviews = new ArrayList<>();
        getSitterReview(sitterId).stream().forEach(review -> reviews.add(mapper.sitterReviewMapper(review.getId())));
        return reviews;
    }

    private boolean checkDataCorrectness(String ownerId, String walkId) {
        Optional<Walk> walk = walkRepository.findById(walkId);
        if (walk.isPresent()) {
            if (walk.get().getOwnerId().equals(ownerId)) {
                Optional<Dog> dog = dogRepository.findById(walk.get().getDogId());
                if (dog.isPresent()) {
                    if(walk.get().isBooked()) {
                        if(!walk.get().isSitterReviewed()) {
                            if (walk.get().getWalkDateTime().isBefore(LocalDateTime.now())) return true;
                            else return false;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    private boolean checkDogReviewDataCorrectness(String sitterId, String walkId) {
        Optional<Walk> walk = walkRepository.findById(walkId);
        if (walk.isPresent()) {
            if (walk.get().getSitterId().equals(sitterId)) {
                Optional<Dog> dog = dogRepository.findById(walk.get().getDogId());
                if (dog.isPresent()) {
                    if(walk.get().isBooked()) {
                        if(!walk.get().isDogReviewed()) {
                            if (walk.get().getWalkDateTime().isBefore(LocalDateTime.now())) return true;
                            else return false;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

}
