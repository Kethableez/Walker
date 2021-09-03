package com.kethableez.walkerapi.User.Model.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.kethableez.walkerapi.Utility.Enum.Gender;
import com.kethableez.walkerapi.Utility.Enum.Role;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String city;
    private String avatar;
    private Gender gender;
    private Role role;
    private Boolean isSubscribed;
}
