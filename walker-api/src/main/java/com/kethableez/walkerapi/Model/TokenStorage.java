package com.kethableez.walkerapi.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    private String token;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    public TokenStorage(String username, String token, String code, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.username = username;
        this.token = token;
        this.code = code;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}
