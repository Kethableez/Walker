package com.kethableez.walkerapi.Controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Entity.UserRole;
import com.kethableez.walkerapi.Model.Enum.Gender;
import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Repository.OwnerRepository;
import com.kethableez.walkerapi.Repository.SitterRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Repository.UserRoleRepository;
import com.kethableez.walkerapi.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestingController {

    @Autowired
    private final UserRoleRepository roleRepository;

    @Autowired
    private final BCryptPasswordEncoder encoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final OwnerRepository ownerRepository;

    @Autowired
    private final SitterRepository sitterRepository;

    @PostMapping("/add_roles")
    public ResponseEntity<?> insertRoles() {
        UserRole admin = new UserRole(Role.ROLE_ADMIN);
        UserRole user = new UserRole(Role.ROLE_USER);
        UserRole owner = new UserRole(Role.ROLE_OWNER);
        UserRole sitter = new UserRole(Role.ROLE_SITTER);

        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(owner);
        roleRepository.save(sitter);

        return ResponseEntity.ok().body(new MessageResponse("Dodano role"));
    }

    @PostMapping("/add_users")
    public ResponseEntity<?> insertUsers() {
        Set<UserRole> roles = new HashSet<>();

        roles.add(roleRepository.findByRole(Role.ROLE_USER).orElseThrow());
        roles.add(roleRepository.findByRole(Role.ROLE_OWNER).orElseThrow());
        User user1 = new User("owner1", "owner1@gmail.com", encoder.encode("owner1"), "John", "Doe",
                LocalDate.of(2000, 10, 10), "test.jpg", Gender.MALE, true, true);
        user1.setRoles(roles);
        userRepository.save(user1);
        roles.clear();
        Owner owner = new Owner(user1);
        ownerRepository.save(owner);

        roles.add(roleRepository.findByRole(Role.ROLE_USER).orElseThrow());
        roles.add(roleRepository.findByRole(Role.ROLE_SITTER).orElseThrow());
        User user2 = new User("sitter1", "sitter1@gmail.com", encoder.encode("sitter1"), "Mark", "Hamill",
                LocalDate.of(1998, 10, 10), "test.jpg", Gender.MALE, true, true);
        user2.setRoles(roles);
        userRepository.save(user2);
        roles.clear();
        Sitter sitter = new Sitter(user2);
        sitterRepository.save(sitter);

        roles.add(roleRepository.findByRole(Role.ROLE_USER).orElseThrow());
        roles.add(roleRepository.findByRole(Role.ROLE_ADMIN).orElseThrow());
        User user3 = new User("admin1", "admin1@gmail.com", encoder.encode("admin1"), "John", "Doe",
                LocalDate.of(2000, 10, 10), "test.jpg", Gender.MALE, true, true);
        user3.setRoles(roles);
        userRepository.save(user3);
        roles.clear();

        return ResponseEntity.ok().body(new MessageResponse("Dodano testowych użytkowników"));
    }

}