package com.kethableez.walkerapi.User.Model.DTO;

import com.kethableez.walkerapi.Utility.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserWithAdditionalInfo {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private Role mainRole;
    private boolean isActive;
    private boolean isBlocked;
    private boolean isBanned;
}
