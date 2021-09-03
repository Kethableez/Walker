package com.kethableez.walkerapi.Utility.Testing;

import com.kethableez.walkerapi.User.Model.Entity.UserRole;
import com.kethableez.walkerapi.User.Repository.UserRoleRepository;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final UserService userService;

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

    @GetMapping("/checkout")
    public String getSomething(UsernamePasswordAuthenticationToken token) {
        String id = userService.getIdFromToken(token);
        return id;
    }   
}