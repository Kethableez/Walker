package com.kethableez.walkerapi.Request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataRequest {
    private String firstName;
    private String lastName;
    private Boolean isSubscribed;
    private LocalDate birthdate;
}
