package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.TokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenStorageRepository extends JpaRepository<TokenStorage, Long> {
    Optional<TokenStorage> findByToken(String token);
}
