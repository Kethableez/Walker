package com.kethableez.walkerapi.Model.Entity;

import javax.persistence.*;

import com.kethableez.walkerapi.Model.Enum.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//INSERT INTO roles(role) VALUES('ROLE_ADMIN');
//INSERT INTO roles(role) VALUES('ROLE_USER');
//INSERT INTO roles(role) VALUES('ROLE_SITTER');
//INSERT INTO roles(role) VALUES('ROLE_OWNER');

@Entity
@Table(name = "roles")
@Getter
@Setter
@RequiredArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole(Long id, Role role) {
        Id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "Id=" + Id +
                ", role=" + role +
                '}';
    }
}
