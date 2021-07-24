package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.TokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

public interface TokenStorageRepository extends JpaRepository<TokenStorage, Long> {
    TokenStorage findByToken(String token);
}
