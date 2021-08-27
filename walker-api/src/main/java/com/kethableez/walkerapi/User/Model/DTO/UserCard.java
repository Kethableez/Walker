package com.kethableez.walkerapi.User.Model.DTO;

import java.time.LocalDate;

import com.kethableez.walkerapi.Utility.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCard {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String avatar;
    private Gender gender;
    private String description;

    //TODO: Add roles
}
