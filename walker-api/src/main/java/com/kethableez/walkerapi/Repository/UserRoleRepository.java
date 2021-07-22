package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Model.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRole(Role role);
}
