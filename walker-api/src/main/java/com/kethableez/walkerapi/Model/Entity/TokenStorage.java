package com.kethableez.walkerapi.Model.Entity;

import java.time.LocalDateTime;

import com.kethableez.walkerapi.Model.Enum.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "token_storage")
@Getter
@Setter
@RequiredArgsConstructor
public class TokenStorage {

    @Id
    private String id;

    private String username;

    // @Enumerated(EnumType.STRING)
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
