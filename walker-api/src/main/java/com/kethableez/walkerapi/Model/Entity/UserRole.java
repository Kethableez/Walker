package com.kethableez.walkerapi.Model.Entity;

import com.kethableez.walkerapi.Model.Enum.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//INSERT INTO roles(role) VALUES('ROLE_ADMIN');
//INSERT INTO roles(role) VALUES('ROLE_USER');
//INSERT INTO roles(role) VALUES('ROLE_SITTER');
//INSERT INTO roles(role) VALUES('ROLE_OWNER');

@Document(collection = "user_roles")
@Getter
@Setter
@RequiredArgsConstructor
public class UserRole {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    // @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole(Role role) {
        this.role = role;
    }
}
