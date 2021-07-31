package com.kethableez.walkerapi.Model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
