package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Entity.Walk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long>{
    
}
