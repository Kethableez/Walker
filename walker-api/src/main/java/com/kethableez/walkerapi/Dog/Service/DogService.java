package com.kethableez.walkerapi.Dog.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Dog.Model.DTO.DogCard;
import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Model.Request.DogRequest;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DogService {
    
    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MapperService mapper;

    public ActionResponse createDog(UsernamePasswordAuthenticationToken token, DogRequest request) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User owner = userRepository.findByUsername(token.getName()).get();

            if (owner.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_OWNER))) {
                Dog dog = new Dog(
                    owner.getId(),
                    request.getName(),
                    request.getDogBreed(),
                    request.getDogType(),
                    request.getWalkDuration(),
                    request.getCharacteristic(),
                    request.getWalkIntensity(),
                    request.getWalkDescription(),
                    request.getDogPhoto()
                );
                dogRepository.save(dog);
                return new ActionResponse(true, dog.getId());
            }
            else return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        }
        else return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");

    }

    public List<DogInfo> getOwnerDogsInfo(String ownerId) {
        List<DogInfo> dogInfos = new ArrayList<>();
        dogRepository.findByOwnerId(ownerId).stream().forEach(dog -> dogInfos.add(mapper.dogInfoMapper(dog.getId())));
        return dogInfos;
    }

    public List<Dog> getSitterDogs(String sitterId) {
        Set<String> dogIds = new HashSet<>();
        for (Walk w : walkRepository.findBySitterId(sitterId)) dogIds.add(w.getDogId());

        List<Dog> dogs = new ArrayList<>();
        for (String id : dogIds) dogs.add(dogRepository.findById(id).get()); 

        return dogs;
    }

    // public List<DogCard> getSitterDogCards(String sitterId) {
    //     Set<String> dogIds = new HashSet<>();
    //     for (Walk w : walkRepository.findBySitterId(sitterId)) dogIds.add(w.getDogId());

    //     List<DogCard> dogs = new ArrayList<>();
    //     for (String id : dogIds) dogs.add(this.createDogCard(this.getDogFromId(id)));

    //     return dogs;
    // }

    public ActionResponse deleteDog(String dogId, String ownerId) {
        if (dogRepository.findByDogIdAndOwnerId(dogId, ownerId).isPresent()) {
            for (Walk w : walkRepository.findByDogId(dogId)) walkRepository.deleteById(w.getId());
            dogRepository.deleteById(dogId);
            return new ActionResponse(true, "Usunięto zwierzaka.");
        }
        else return new ActionResponse(false, "Nie stworzyłeś tego zwierzaka");
    }

    public DogCard getDogCard(String dogId) {
        return mapper.dogCardMapper(dogId);
    }

    public Dog getDogFromId(String dogId) {
        return this.dogRepository.findById(dogId).orElseThrow();
    }
}
