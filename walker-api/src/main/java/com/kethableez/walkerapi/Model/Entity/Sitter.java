package com.kethableez.walkerapi.Model.Entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "sitters")
@Setter
@Getter
@RequiredArgsConstructor
public class Sitter {

    @Id
    private String id;

    private User user;

    private Set<Walk> walks = new HashSet<>();

    public Sitter(User user) {
        this.user = user;
    }
}
