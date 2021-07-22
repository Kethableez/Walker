package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterRepository extends JpaRepository<Sitter, Long> {
    @Query(
            value = "select * from walker.sitters where user_id = ?1",
            nativeQuery = true
    )
    Sitter findByUserId(Long userId);
}
