package com.kethableez.walkerapi.Review.Model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogReviewRequest {
    private String walkId;
    private String dogPhoto;
    private String reviewBody;
}
