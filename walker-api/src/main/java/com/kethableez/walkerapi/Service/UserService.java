package com.kethableez.walkerapi.Service;

import com.kethableez.walkerapi.Config.Security.PasswordEncoder;
import com.kethableez.walkerapi.Model.Role;
import com.kethableez.walkerapi.Model.TokenStorage;
import com.kethableez.walkerapi.Model.User;
import com.kethableez.walkerapi.Model.UserRole;
import com.kethableez.walkerapi.Repository.TokenStorageRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Repository.UserRoleRepository;
import com.kethableez.walkerapi.Request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserRoleRepository roleRepository;

    @Autowired
    private final TokenStorageRepository tokenStorageRepository;

    @Autowired
    private final PasswordEncoder encoder;

    public User registerUser(RegisterRequest request){
        User newUser = new User(
                request.getUsername(),
                request.getEmail(),
                encoder.bCryptPasswordEncoder().encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate(),
                "default.png",
                request.getGender(),
                true,
                request.getIsSubscribed()
        );
        Set<UserRole> roles = new HashSet<>();
        roles.add(
                roleRepository.findByRole(Role.ROLE_USER).orElseThrow()
        );
        roles.add(
                roleRepository.findByRole(request.getRole()).orElseThrow()
        );
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    public User registerAdmin(RegisterRequest request){
        Random rnd = new Random();
        int code = rnd.nextInt(999999);
        User newUser = new User(
                request.getUsername(),
                request.getEmail(),
                encoder.bCryptPasswordEncoder().encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate(),
                "default.png",
                request.getGender(),
                true,
                request.getIsSubscribed()
        );
        Set<UserRole> roles = new HashSet<>();
        roles.add(
                roleRepository.findByRole(request.getRole()).orElseThrow()
        );
        newUser.setRoles(roles);

        TokenStorage storage = new TokenStorage(
                request.getUsername(),
                UUID.randomUUID().toString(),
                String.format("%06d", code),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20)
        );

        tokenStorageRepository.save(storage);
        return userRepository.save(newUser);
    }

    public User confirmAdmin(TokenStorage token) {
        User admin = userRepository.findByUsername(token.getUsername()).orElseThrow();
        Set<UserRole> roles = admin.getRoles();
        roles.add(
                roleRepository.findByRole(Role.ROLE_ADMIN).orElseThrow()
        );
        admin.setRoles(roles);
        return userRepository.save(admin);
    }
}
