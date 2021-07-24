package com.kethableez.walkerapi.Service;

import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Repository.SitterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SitterService {
    @Autowired
    private final SitterRepository sitterRepository;

    public Sitter createSitter(User user) {
        Sitter newSitter = new Sitter(
                user
        );
        return sitterRepository.save(newSitter);
    }

    public Sitter getData(Long userId){
        return this.sitterRepository.findByUserId(userId);
    }
}
