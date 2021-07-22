package com.kethableez.walkerapi.Service;

import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private final OwnerRepository ownerRepository;

    public Owner createOwner(User user) {
        Owner newOwner = new Owner(
                user
        );
        return ownerRepository.save(newOwner);
    }

    public Owner getData(Long userId){
//        return this.ownerRepository.getById(1L);
        return this.ownerRepository.findByUserId(userId);
    }
}
