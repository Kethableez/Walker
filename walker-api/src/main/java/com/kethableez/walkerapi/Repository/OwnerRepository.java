package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query(
            value = "select * from walker.owners where user_id = ?1",
            nativeQuery = true
    )
    Owner findByUserId(Long userId);
}
