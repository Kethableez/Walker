package com.kethableez.walkerapi.User.Model.DTO;

import java.time.LocalDate;
import java.util.Set;

import com.kethableez.walkerapi.Utility.Enum.Gender;
import com.kethableez.walkerapi.Utility.Enum.Role;

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
    private String city;
    private String avatar;
    private Gender gender;
    private String description;
    private Set<Role> roles;
}