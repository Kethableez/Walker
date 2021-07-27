package com.kethableez.walkerapi.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
