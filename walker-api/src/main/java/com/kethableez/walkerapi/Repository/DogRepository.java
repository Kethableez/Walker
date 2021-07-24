package com.kethableez.walkerapi.Repository;

import java.util.List;

import com.kethableez.walkerapi.Model.Entity.Dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long>{
    @Query(
        value = "select * from walker.dogs where owner = ?1",
        nativeQuery = true
    )
    List<Dog> findByUserId(String username);

}
