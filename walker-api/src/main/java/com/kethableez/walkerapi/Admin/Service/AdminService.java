package com.kethableez.walkerapi.Admin.Service;

import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.User.Model.DTO.UserWithAdditionalInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final WalkRepository walkRepository;
    private final MapperService mapperService;

    @Autowired
    public AdminService(UserRepository userRepository, DogRepository dogRepository, WalkRepository walkRepository, MapperService mapperService) {
        this.userRepository = userRepository;
        this.dogRepository = dogRepository;
        this.walkRepository = walkRepository;
        this.mapperService = mapperService;
    }

    public List<UserWithAdditionalInfo> getUsersList() {
        return List.of(
            userRepository.findAll().stream()
            .map(user -> mapperService.userWithAdditionalInfoMapper(user.getId()))
            .toArray(UserWithAdditionalInfo[]::new)
            );
    }

    public List<Walk> getWalksList() {
        return walkRepository.findAll();
    }

    public List<Dog> getDogsList() {
        return dogRepository.findAll();
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
