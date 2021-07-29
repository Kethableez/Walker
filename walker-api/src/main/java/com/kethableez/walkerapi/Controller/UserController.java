package com.kethableez.walkerapi.Controller;

import java.util.List;

import com.kethableez.walkerapi.Config.Security.PasswordEncoder;
import com.kethableez.walkerapi.Model.Entity.Owner;
import com.kethableez.walkerapi.Model.Entity.Sitter;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Request.UserDataRequest;
import com.kethableez.walkerapi.Request.UserPasswordRequest;
import com.kethableez.walkerapi.Response.MessageResponse;
import com.kethableez.walkerapi.Service.OwnerService;
import com.kethableez.walkerapi.Service.SitterService;
import com.kethableez.walkerapi.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final OwnerService ownerService;

    @Autowired
    private final SitterService sitterService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder encoder;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get_data")
    public ResponseEntity<?> getData(UsernamePasswordAuthenticationToken token) {
        User user = userRepository.findByUsername(token.getName()).orElseThrow();

        Role mainRole = Role.ROLE_USER;
        for (GrantedAuthority authority : token.getAuthorities()) {
            if (authority.getAuthority().equals(Role.ROLE_OWNER.toString()))
                mainRole = Role.ROLE_OWNER;
            else if (authority.getAuthority().equals(Role.ROLE_SITTER.toString()))
                mainRole = Role.ROLE_SITTER;
        }
        switch (mainRole) {
            case ROLE_OWNER:
                Owner owner = ownerService.getData(user.getUsername());
                return new ResponseEntity<>(owner, HttpStatus.OK);

            case ROLE_SITTER:
                Sitter sitter = sitterService.getData(user.getUsername());
                return new ResponseEntity<>(sitter, HttpStatus.OK);

            default:
                return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/change_data")
    public ResponseEntity<?> changeData(UsernamePasswordAuthenticationToken token,
            @RequestBody UserDataRequest request) {
        this.userService.changeData(token, request);
        return ResponseEntity.ok(new MessageResponse("Zmieniono dane pomyślnie"));
    }

    @PutMapping("/change_password")
    public ResponseEntity<?> changePassword(UsernamePasswordAuthenticationToken token,
            @RequestBody UserPasswordRequest request) {
        User user = userRepository.findByUsername(token.getName()).get();

        if (encoder.bCryptPasswordEncoder().matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.bCryptPasswordEncoder().encode(request.getNewPassword()));
            if (user.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_OWNER))) {
                this.ownerService.changeData(user);
            } else if (user.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ROLE_SITTER))) {
                this.sitterService.changeData(user);
            }
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Zmieniono dane pomyślnie"));
        } else {
            return ResponseEntity.badRequest().body("Złe hasło");
        }

    }
}
