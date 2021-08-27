package com.kethableez.walkerapi.User.Repository;

import java.util.Optional;

import com.kethableez.walkerapi.User.Model.Entity.UserRole;
import com.kethableez.walkerapi.Utility.Enum.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String>{
    Optional<UserRole> findByRole(Role role);
}
