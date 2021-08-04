package com.kethableez.walkerapi.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Model.DTO.UserInfo;
import com.kethableez.walkerapi.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Model.Entity.Dog;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.Walk;
import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Model.Request.WalkRequest;
import com.kethableez.walkerapi.Model.Response.ActionResponse;
import com.kethableez.walkerapi.Repository.DogRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Repository.WalkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalkService {

    @Autowired
    private final WalkRepository walkRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DogRepository dogRepository;

    @Autowired
    private final UserService userService;

    public ActionResponse createWalk(UsernamePasswordAuthenticationToken token, WalkRequest request) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User owner = userRepository.findByUsername(token.getName()).get();

            if (owner.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_OWNER))) {
                Walk walk = new Walk(request.getDogId(), owner.getId(), request.getWalkDateTime(), request.getCity(),
                        request.getAddress(), request.getWalkLat(), request.getWalkLon(), request.getWalkDescription(),
                        false);

                walkRepository.save(walk);
                return new ActionResponse(true, "Stworzono spacer.");
            } else
                return new ActionResponse(false, "Nie posiadasz uprawnień aby wykonać tą akcję.");
        } else
            return new ActionResponse(false, "Nie znaleziono takiego użytkownika.");
    }

    public List<Walk> getOwnerWalks(String ownerId) {
        return walkRepository.findByOwnerId(ownerId);
    }

    public List<Walk> getSitterWalks(String sitterId) {
        return walkRepository.findByWalkDateTimeGreaterThanAndSitterId(LocalDateTime.now(), sitterId);
    }

    public List<Walk> getSitterHistoryWalk(String sitterId) {
        return walkRepository.findByWalkDateTimeLessThanAndSitterId(LocalDateTime.now(), sitterId);
    }

    public List<WalkCard> getSitterWalkCards(String sitterId) {
        List<WalkCard> walks = new ArrayList<>();
        for(Walk w : walkRepository.findByWalkDateTimeGreaterThanAndSitterId(LocalDateTime.now(), sitterId)) walks.add(createCard(w));

        return walks;
    }

    public List<WalkCard> getSitterHistoryWalkCards(String sitterId) {
        List<WalkCard> walks = new ArrayList<>();
        for(Walk w :  walkRepository.findByWalkDateTimeLessThanAndSitterId(LocalDateTime.now(), sitterId)) walks.add(createCard(w));

        return walks;
    }

    public List<Walk> getDogFutureWalks(String dogId) {
        Pageable page = PageRequest.of(0, 5);
        return walkRepository.findByWalkDateTimeGreaterThanAndDogIdOrderByWalkDateTimeAsc(LocalDateTime.now(), dogId,
                page);
    }

    public List<UserInfo> getSitters(String ownerId) {
        Set<String> sitterIds = new HashSet<>();
        for (Walk w : this.getOwnerWalks(ownerId)) {
            if (w.isBooked())
                sitterIds.add(w.getSitterId());
        }
        List<UserInfo> sitters = new ArrayList<>();
        for (String id : sitterIds)
            sitters.add(userService.getUserInfo(id));
        return sitters;
    }

    public ActionResponse walkEnroll(UsernamePasswordAuthenticationToken token, String walkId) {
        if (userRepository.findByUsername(token.getName()).isPresent()) {
            User sitter = userRepository.findByUsername(token.getName()).get();
            if (sitter.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_SITTER))) {
                if (walkRepository.findById(walkId).isPresent()) {
                    Walk walk = walkRepository.findById(walkId).get();
                    if (walk.isBooked())
                        return new ActionResponse(false, "Nie można się już zapisać na ten spacer.");
                    else {
                        walk.setBooked(true);
                        walk.setSitterId(sitter.getId());
                        walkRepository.save(walk);
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

            if (sitter.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_SITTER))) {

                if (walkRepository.findByWalkIdAndSitterId(walkId, sitter.getId()).isPresent()) {
                    Walk walk = walkRepository.findByWalkIdAndSitterId(walkId, sitter.getId()).get();

                    walk.setBooked(false);
                    walk.setSitterId(null);
                    walkRepository.save(walk);
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
            return new ActionResponse(true, "Usunięto spacer.");
        } else
            return new ActionResponse(false, "Nie stworzyłeś tego spaceru");
    }

    public List<Walk> getWalks() {
        return this.walkRepository.findByWalkDateTimeGreaterThanAndIsBooked(LocalDateTime.now(), false);
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
        UserInfo ownerInfo = new UserInfo(owner.getFirstName(), owner.getLastName(), owner.getUsername(),
                owner.getAvatar());

        return new WalkCard(walk, dog, ownerInfo);
    }

    public Walk getWalkFromId(String walkId){
        return this.walkRepository.findById(walkId).orElseThrow();
    }
}
