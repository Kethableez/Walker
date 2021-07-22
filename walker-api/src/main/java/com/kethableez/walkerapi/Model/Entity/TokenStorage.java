package com.kethableez.walkerapi.Model.Entity;

import com.kethableez.walkerapi.Model.Enum.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token_storage")
@Getter
@Setter
@RequiredArgsConstructor
public class TokenStorage {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String token;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    private boolean isConfirmed;

    public TokenStorage(String username, Role role, String token, String code, LocalDateTime createdAt, LocalDateTime expiredAt, boolean isConfirmed) {
        this.username = username;
        this.role = role;
        this.token = token;
        this.code = code;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.isConfirmed = isConfirmed;
    }
}
