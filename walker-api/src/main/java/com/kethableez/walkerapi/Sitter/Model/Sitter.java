package com.kethableez.walkerapi.Sitter.Model;

import java.util.List;

import com.kethableez.walkerapi.Review.Model.DTO.SitterReviewCard;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sitter {
    private UserCard sitter;
    private Float rating;
    private List<SitterReviewCard> reviews;
    private List<String> images;
}
