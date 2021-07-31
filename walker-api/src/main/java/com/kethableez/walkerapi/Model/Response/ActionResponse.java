package com.kethableez.walkerapi.Model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionResponse {
    private boolean isSuccess;
    private String message;

    public ActionResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
