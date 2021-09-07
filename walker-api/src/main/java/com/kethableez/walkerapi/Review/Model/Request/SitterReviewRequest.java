package com.kethableez.walkerapi.Review.Model.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SitterReviewRequest {
    private String walkId;
    private Float rating;
    private String reivewBody;
}
