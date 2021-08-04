package com.kethableez.walkerapi.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.DTO.DogCard;
import com.kethableez.walkerapi.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Model.Request.DogRequest;
import com.kethableez.walkerapi.Model.Response.ActionResponse;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;

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
    private final WalkService walkService;

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
                return new ActionResponse(true, "Dodano nowego zwierzaka.");
            }
            else return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        }
        else return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");

    }

    public List<DogCard> getOwnerDogs(String ownerId) {
        List<DogCard> dogCards = new ArrayList<>();
        for(Dog d : dogRepository.findByOwnerId(ownerId)) {
            DogCard dc = this.createDogCard(d);
            dogCards.add(dc);
        }

        return dogCards;
    }

    public List<Dog> getSitterDogs(String sitterId) {
        Set<String> dogIds = new HashSet<>();
        for (Walk w : walkRepository.findBySitterId(sitterId)) dogIds.add(w.getDogId());

        List<Dog> dogs = new ArrayList<>();
        for (String id : dogIds) dogs.add(dogRepository.findById(id).get()); 

        return dogs;
    }

    public List<DogCard> getSitterDogCards(String sitterId) {
        Set<String> dogIds = new HashSet<>();
        for (Walk w : walkRepository.findBySitterId(sitterId)) dogIds.add(w.getDogId());

        List<DogCard> dogs = new ArrayList<>();
        for (String id : dogIds) dogs.add(this.createDogCard(this.getDogFromId(id)));

        return dogs;
    }

    public ActionResponse deleteDog(String dogId, String ownerId) {
        if (dogRepository.findByDogIdAndOwnerId(dogId, ownerId).isPresent()) {
            for (Walk w : walkRepository.findByDogId(dogId)) System.out.println(w.getId());
            dogRepository.deleteById(dogId);
            return new ActionResponse(true, "Usunięto zwierzaka.");
        }
        else return new ActionResponse(false, "Nie stworzyłeś tego zwierzaka");
    }

    public DogCard createDogCard(Dog dog) {
        User owner = userRepository.findById(dog.getOwnerId()).orElseThrow();
        UserInfo ownerInfo = new UserInfo(owner.getFirstName(), owner.getLastName(), owner.getUsername(), owner.getAvatar());
        List<Walk> incomingWalks = walkService.getDogFutureWalks(dog.getId());
        return new DogCard(dog, ownerInfo, incomingWalks);
    }

    public Dog getDogFromId(String dogId) {
        return this.dogRepository.findById(dogId).orElseThrow();
    }
}
