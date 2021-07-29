package com.kethableez.walkerapi.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.Model.Entity.UserRole;
import com.kethableez.walkerapi.Model.Enum.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String>{
    Optional<UserRole> findByRole(Role role);
}
