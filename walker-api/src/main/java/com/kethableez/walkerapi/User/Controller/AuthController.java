package com.kethableez.walkerapi.User.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.kethableez.walkerapi.Config.Jwt.JwtResponse;
import com.kethableez.walkerapi.Config.Jwt.JwtUtils;
import com.kethableez.walkerapi.Config.Security.PasswordEncoder;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Model.Request.LoginRequest;
import com.kethableez.walkerapi.User.Model.Request.RegisterRequest;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.User.Service.UserDetailsImpl;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtUtils jwtUtils;

    private static final String registerToken = "05da579b-cafe-4395-8eeb-88826dfd6cc9";

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Taki użytkownik nie istnieje!");
        } else {
            if (!user.get().getIsActive()) {
                return ResponseEntity.badRequest().body("Konto nie jest aktywowane!!");
            } else {
                if (encoder.bCryptPasswordEncoder().matches(request.getPassword(), user.get().getPassword())) {
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String jwt = jwtUtils.generateJwtToken(authentication);

                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                            .collect(Collectors.toList());

                    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                            userDetails.getEmail(), roles));
                } else {
                    return ResponseEntity.badRequest().body("Nazwa użytkownika lub hasło jest niepoprawne");
                }
            }
        }
    }

    // TODO: Better response handling!
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Użytkownik o takiej nazwie już istnieje");
        }

        else if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Użytkownik o takim adresie e-mail już istnieje");
        }

        else if (request.getBirthdate().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Zła data urodzin!");
        } else {
            String userId = userService.registerUser(request);
            return ResponseEntity.ok(new MessageResponse(userId));
        }
    }

    @PostMapping("/register/" + registerToken)
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Użytkownik o takiej nazwie już istnieje");
        }

        else if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Użytkownik o takim adresie e-mail już istnieje");
        }

        else if (request.getBirthdate().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Zła data urodzin!");
        }

        else {
            String userId = userService.registerAdmin(request);
            return ResponseEntity.ok(new MessageResponse(userId));
        }
    }

    @PutMapping("/confirm/{token}")
    public ResponseEntity<?> confirmUser(@PathVariable("token") String token, @RequestBody String code) {
        ActionResponse response = userService.confirmUser(token, code);
        
        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }
}
