package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.Sitter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterRepository extends JpaRepository<Sitter, Long> {
    @Query(value = "select * from walker.sitters where user_id = ?1", nativeQuery = true)
    Sitter findByUserId(Long userId);

    @Query(value = "select * from walker.sitters where user_id = (select id from walker.users where username = ?1)", nativeQuery = true)
    Sitter findByUsername(String username);

    // select * from sitter_walks inner join walks on sitter_walks.walk_id=walks.id inner join dogs on walks.dog_id = dogs.id; double join
    // select dog_id from sitter_walks inner join walks on sitter_walks.walk_id=walks.id;

    
    //select sitter_id from sitter_walks inner join walks on sitter_walks.walk_id=walks.id where dog_id = 1;
}
