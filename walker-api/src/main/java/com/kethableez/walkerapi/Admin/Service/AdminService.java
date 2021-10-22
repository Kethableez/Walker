package com.kethableez.walkerapi.Admin.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.User.Model.DTO.UserWithAdditionalInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Model.Activity;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Services.ActivityService;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final WalkRepository walkRepository;
    private final MapperService mapperService;
    private final ActivityService activityService;

    @Autowired
    public AdminService(UserRepository userRepository, DogRepository dogRepository, WalkRepository walkRepository, MapperService mapperService, ActivityService activityService) {
        this.userRepository = userRepository;
        this.dogRepository = dogRepository;
        this.walkRepository = walkRepository;
        this.mapperService = mapperService;
        this.activityService = activityService;
    }

    public List<UserWithAdditionalInfo> getUsersList() {
        return List.of(
            userRepository.findAll().stream()
            .map(user -> mapperService.userWithAdditionalInfoMapper(user.getId()))
            .toArray(UserWithAdditionalInfo[]::new)
            );
    }

    public List<WalkCard> getWalksList() {
        return walkRepository.findAll().stream()
        .map(walk -> mapperService.walkCardMapper(walk.getId()))
        .collect(Collectors.toList());
    }

    public List<DogCard> getDogsList() {
        return dogRepository.findAll().stream()
        .map(dog -> mapperService.dogCardMapper(dog.getId()))
        .collect(Collectors.toList());
    }

    public List<Activity> getUserActivities() {
        return activityService.getAllActivities();
    }

    public ActionResponse blockUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setIsBlocked(true);
            userRepository.save(user.get());
            return new ActionResponse(true, "Zablokowano użytkownika");
        }
        else return new ActionResponse(false, "Wystapił błąd");
    }

    public ActionResponse banUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setIsBanned(true);
            userRepository.save(user.get());
            return new ActionResponse(true, "Zbanowano użytkownika");
        }
        else return new ActionResponse(false, "Wystapił błąd");
    }

    public ActionResponse unblockUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setIsBlocked(false);
            userRepository.save(user.get());
            return new ActionResponse(true, "Odblokowano użytkownika");
        }
        else return new ActionResponse(false, "Wystapił błąd");
    }

    public ActionResponse unbanUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setIsBanned(false);
            userRepository.save(user.get());
            return new ActionResponse(true, "Odbanowano użytkownika");
        }
        else return new ActionResponse(false, "Wystapił błąd");
    }
}
