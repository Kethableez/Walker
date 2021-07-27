package com.kethableez.walkerapi.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.kethableez.walkerapi.Model.Entity.Dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long>{
    @Query(
        value = "select * from walker.dogs where owner = ?1",
        nativeQuery = true
    )
    List<Dog> findByUserId(String username);

    @Modifying
    @Transactional
    @Query(value = "delete from walker.owner_dogs where dog_id = ?1", nativeQuery = true)
    void deleteFromOwnerDogs(Long dogId);

    @Modifying
    @Transactional
    @Query(value = "delete from walker.dogs where id = ?1", nativeQuery = true)
    void deleteDog(Long dogId);

}
