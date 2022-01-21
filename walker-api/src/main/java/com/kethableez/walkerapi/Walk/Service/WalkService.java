package com.kethableez.walkerapi.Walk.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.Dog.Repository.DogRepository;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.Utility.Address.Controller.AddressController;
import com.kethableez.walkerapi.Utility.Address.Model.District;
import com.kethableez.walkerapi.Utility.Address.Service.AddressService;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Model.ActivityType;
import com.kethableez.walkerapi.Utility.Model.NotificationType;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Services.ActivityService;
import com.kethableez.walkerapi.Utility.Services.NotificationService;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkWithFilters;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;
import com.kethableez.walkerapi.Walk.Model.Request.WalkRequest;
import com.kethableez.walkerapi.Walk.Model.Utility.WalkFilter;
import com.kethableez.walkerapi.Walk.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final MapperService mapper;
    private final ActivityService activityService;
    private final NotificationService notificationService;
    private final AddressService addressService;

    @Autowired
    public WalkService(WalkRepository walkRepository, UserRepository userRepository, DogRepository dogRepository, MapperService mapper, ActivityService activityService, NotificationService notificationService, AddressService addressService) {
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        this.dogRepository = dogRepository;
        this.mapper = mapper;
        this.activityService = activityService;
        this.notificationService = notificationService;
        this.addressService = addressService;
    }



    public ActionResponse createWalk(UsernamePasswordAuthenticationToken token, WalkRequest request) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            System.out.println(request.getZipCode());
            User owner = userRepository.findByUsername(token.getName()).get();
            District walkDistrict = addressService.findCity(request.getZipCode());

            if (owner.getRoles().stream().anyMatch(r -> r.equals(Role.ROLE_OWNER))) {
                Walk walk = new Walk(
                    request.getDogId(), 
                    owner.getId(), 
                    request.getWalkDateTime(),
                    request.getZipCode(),
                    request.getCity(),
                    walkDistrict.getDistrictCode(),
                    walkDistrict.getRegionCode(),
                    request.getAddress(), 
                    request.getWalkLat(), 
                    request.getWalkLon(), 
                    request.getWalkDescription(),
                    false, 
                    false, 
                    false);

                walkRepository.save(walk);
                this.activityService.reportActivity(owner.getId(), ActivityType.WALK_CREATE);
                return new ActionResponse(true, "Stworzono spacer.");
            } else
                return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        } else
            return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");
    }

    public List<WalkCard> getOwnerWalks(String ownerId) {
        return walkRepository.findByOwnerId(ownerId).stream().map(walk -> mapper.walkCardMapper(walk.getId())).collect(Collectors.toList());
    }

    public List<WalkCard> getOwnerIncomingWalks(String ownerId) {
        return this.getOwnerWalks(ownerId).stream()
        .filter(walkCard -> walkCard.getWalk().getWalkDateTime().isAfter(LocalDateTime.now()))
        .collect(Collectors.toList());
    }

    public List<WalkCard> getOwnerPastWalks(String ownerId) {
        return this.getOwnerWalks(ownerId).stream()
        .filter(walkCard -> walkCard.getWalk().getWalkDateTime().isBefore(LocalDateTime.now()))
        .collect(Collectors.toList());
    }

    public List<WalkCard> getSitterWalks(String sitterId) {
        return walkRepository.findBySitterId(sitterId).stream().map(walk -> mapper.walkCardMapper(walk.getId())).collect(Collectors.toList());
    }

    public List<WalkCard> getSitterIncomingWalks(String sitterId) {
        return this.getSitterWalks(sitterId).stream()
        .filter(walkCard -> walkCard.getWalk().getWalkDateTime().isAfter(LocalDateTime.now()))
        .collect(Collectors.toList());
    }

    public List<WalkCard> getSitterPastWalks(String sitterId) {
        return this.getSitterWalks(sitterId).stream()
        .filter(walkCard -> walkCard.getWalk().getWalkDateTime().isBefore(LocalDateTime.now()))
        .collect(Collectors.toList());
    }

    public ActionResponse walkEnroll(UsernamePasswordAuthenticationToken token, String walkId) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User sitter = userRepository.findByUsername(token.getName()).get();
            if (sitter.getRoles().stream().anyMatch(r -> r.equals(Role.ROLE_SITTER))) {
                if (walkRepository.findById(walkId).isPresent()) {
                    Walk walk = walkRepository.findById(walkId).get();
                    if (walk.isBooked())
                        return new ActionResponse(false, "Nie można się już zapisać na ten spacer.");
                    else {
                        walk.setBooked(true);
                        walk.setSitterId(sitter.getId());
                        walkRepository.save(walk);
                        activityService.reportActivity(sitter.getId(), ActivityType.WALK_ENROLLMENT);
                        notificationService.createNotification(sitter.getId(), walk.getOwnerId(), walk.getId(), NotificationType.WALK_ENROLLMENT);
                        return new ActionResponse(true, "Zapisano na spacer.");
                    }

                } else
                    return new ActionResponse(false, "Nie znaleziono podanego spaceru.");
            } else
                return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        } else
            return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");
    }

    public ActionResponse walkDisenroll(UsernamePasswordAuthenticationToken token, String walkId) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User sitter = userRepository.findByUsername(token.getName()).get();

            if (sitter.getRoles().stream().anyMatch(r -> r.equals(Role.ROLE_SITTER))) {

                if (walkRepository.findByWalkIdAndSitterId(walkId, sitter.getId()).isPresent()) {
                    Walk walk = walkRepository.findByWalkIdAndSitterId(walkId, sitter.getId()).get();

                    walk.setBooked(false);
                    walk.setSitterId(null);
                    walkRepository.save(walk);
                    activityService.reportActivity(sitter.getId(), ActivityType.WALK_DISENROLLMENT);
                    notificationService.createNotification(sitter.getId(), walk.getOwnerId(), walk.getId(), NotificationType.WALK_DISENROLLMENT);
                    return new ActionResponse(true, "Wypisano ze spaceru.");

                } else
                    return new ActionResponse(false, "Nie jesteś zapisany na taki spacer.");
            } else
                return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        } else
            return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");
    }

    public ActionResponse deleteWalk(String walkId, String ownerId) {
        if (walkRepository.findByWalkIdAndOwnerId(walkId, ownerId).isPresent()) {
            this.walkRepository.deleteById(walkId);
            activityService.reportActivity(ownerId, ActivityType.WALK_REMOVE);
            return new ActionResponse(true, "Usunięto spacer.");
        } else
            return new ActionResponse(false, "Nie stworzyłeś tego spaceru");
    }

    public List<Walk> getWalks() {
        return this.walkRepository.findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime.now(), false);
    }

    // Przemyśleć to jeszczce ze 3 razy
    public WalkWithFilters getWalkWithFilters() {
        List<WalkCard> walkCards = walkRepository.findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime.now(), false).stream()
        .map(walk -> mapper.walkCardMapper(walk.getId()))
        .collect(Collectors.toList());
        return new WalkWithFilters(walkCards, new WalkFilter(walkCards));
    }

    public WalkWithFilters getWalkWithFiltersByCity(String districtCode) {
        List<WalkCard> walkCards = walkRepository.findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime.now(), false).stream()
        .filter(walk -> walk.getDistrictCode().equals(districtCode))
        .map(walk -> mapper.walkCardMapper(walk.getId()))
        .collect(Collectors.toList());
        return new WalkWithFilters(walkCards, new WalkFilter(walkCards));
    }

    public List<WalkCard> getAllAvailableWalkCards() {
        List<WalkCard> walkInfos = new ArrayList<>();        
        this.getWalks().stream().forEach(w -> walkInfos.add(mapper.walkCardMapper(w.getId())));
        return walkInfos;
    }

    public List<WalkCard> getWalkCards() {
        List<WalkCard> walkCards = new ArrayList<>();
        for (Walk w : this.getWalks()) {
            WalkCard wc = createCard(w);
            walkCards.add(wc);
        }
        return walkCards;
    }

    // Walidacje!
    public WalkCard createCard(Walk walk) {
        Dog dog = dogRepository.findById(walk.getDogId()).orElseThrow();
        User owner = userRepository.findById(walk.getOwnerId()).orElseThrow();
        UserCard userCard = new UserCard(
            owner.getId(),
            owner.getUsername(),
            owner.getFirstName(),
            owner.getLastName(),
            owner.getBirthdate(),
            owner.getZipCode(),
            owner.getCity(),
            owner.getDistrictCode(),
            owner.getRegionCode(),
            owner.getAvatar(),
            owner.getGender(),
            owner.getDescription(),
            owner.getRoles()
        );

        return new WalkCard(walk, dog, userCard);
    }

    public Walk getWalkFromId(String walkId){
        return this.walkRepository.findById(walkId).orElseThrow();
    }
}
