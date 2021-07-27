package com.kethableez.walkerapi.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.Walk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long>{
    
    @Modifying
    @Transactional
    @Query(value = "delete from walker.walks where id = ?1", nativeQuery = true)
    void deleteWalk(Long walkId);

    @Modifying
    @Transactional
    @Query(value = "delete from walker.walks where dog_id = ?1", nativeQuery = true)
    void deleteWalkByDogId(Long dogId);

    @Query(value = "select * from walker.walks where dog_id = ?1", nativeQuery = true)
    List<Walk> selectWallksByDogId(Long dogId);

    @Query(value = "select sitter_id from walker.sitter_walks where walk_id = ?1", nativeQuery = true)
    Long getSitter(Long walkId);
}
