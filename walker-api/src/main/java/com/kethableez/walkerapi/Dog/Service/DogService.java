package com.kethableez.walkerapi.Dog.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Model.Request.DogRequest;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Model.ActivityType;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Services.ActivityService;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private final DogRepository dogRepository;
    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private final MapperService mapper;
    private final ActivityService activityService;

    @Autowired
    public DogService(DogRepository dogRepository, WalkRepository walkRepository, UserRepository userRepository,
            MapperService mapper, ActivityService activityService) {
        this.dogRepository = dogRepository;
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.activityService = activityService;
    }

    public ActionResponse createDog(UsernamePasswordAuthenticationToken token, DogRequest request) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User owner = userRepository.findByUsername(token.getName()).get();

            if (owner.getRoles().stream().anyMatch(r -> r.equals(Role.ROLE_OWNER))) {
                Dog dog = new Dog(owner.getId(), request.getName(), request.getDogBreed(), request.getDogType(),
                        request.getWalkDuration(), request.getCharacteristic(), request.getWalkIntensity(),
                        request.getWalkDescription(), request.getDogPhoto());
                dogRepository.save(dog);
                activityService.reportActivity(owner.getId(), ActivityType.WALK_CREATE);
                return new ActionResponse(true, dog.getId());
            } else
                return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        } else
            return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");

    }

    public List<Dog> getSitterDogs(String sitterId) {
        Set<String> dogIds = new HashSet<>();
        for (Walk w : walkRepository.findBySitterId(sitterId))
            dogIds.add(w.getDogId());

        List<Dog> dogs = new ArrayList<>();
        for (String id : dogIds)
            dogs.add(dogRepository.findById(id).get());

        return dogs;
    }

    public List<DogCard> getOwnerDogs(String ownerId) {
        return this.dogRepository.findByOwnerId(ownerId).stream().map(dog -> mapper.dogCardMapper(dog.getId()))
                .collect(Collectors.toList());
    }

    public ActionResponse deleteDog(String dogId, String ownerId) {
        if (dogRepository.findByDogIdAndOwnerId(dogId, ownerId).isPresent()) {
            for (Walk w : walkRepository.findByDogId(dogId))
                walkRepository.deleteById(w.getId());
            dogRepository.deleteById(dogId);
            activityService.reportActivity(ownerId, ActivityType.DOG_REMOVE);
            return new ActionResponse(true, "Usunięto zwierzaka.");
        } else
            return new ActionResponse(false, "Nie stworzyłeś tego zwierzaka");
    }

    public DogCard getDogCard(String dogId) {
        return mapper.dogCardMapper(dogId);
    }

    public Dog getDogFromId(String dogId) {
        return this.dogRepository.findById(dogId).orElseThrow();
    }
}
