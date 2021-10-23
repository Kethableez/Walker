package com.kethableez.walkerapi.Walk.Model.DTO;

import com.kethableez.walkerapi.Dog.Model.Entity.Dog;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.Walk.Model.Entity.Walk;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WalkCard {
    private Walk walk;
    private Dog dog;
    private UserCard owner;
    private UserCard sitter;
    
    public WalkCard(Walk walk, Dog dog, UserCard owner) {
        this.walk = walk;
        this.dog = dog;
        this.owner = owner;
    }

    public WalkCard(Walk walk, Dog dog, UserCard owner, UserCard sitter) {
        this.walk = walk;
        this.dog = dog;
        this.owner = owner;
        this.sitter = sitter;
    }
}
    