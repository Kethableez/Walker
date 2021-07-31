package com.kethableez.walkerapi.Model.Request;

import com.kethableez.walkerapi.Model.Enum.Gender;
import com.kethableez.walkerapi.Model.Enum.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Gender gender;
    private Role role;
    private Boolean isSubscribed;
}
