package com.kethableez.walkerapi.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserInfo {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String avatar;
}
