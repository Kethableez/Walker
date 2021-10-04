package com.kethableez.walkerapi.Owner.Model;

import java.util.List;

import com.kethableez.walkerapi.Dog.Model.DTO.DogInfo;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Owner {
    private UserCard user;
    private List<DogInfo> dogs;
    private List<WalkInfo> plannedWalks;
    private List<WalkInfo> pastWalks;
    private List<String> dogImages;
}
