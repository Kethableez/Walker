package com.kethableez.walkerapi.Controller;

import com.kethableez.walkerapi.Config.Jwt.JwtResponse;
import com.kethableez.walkerapi.Config.Jwt.JwtUtils;
import com.kethableez.walkerapi.Model.Enum.Role;
import com.kethableez.walkerapi.Model.Entity.TokenStorage;
import com.kethableez.walkerapi.Model.Entity.User;
import com.kethableez.walkerapi.Repository.TokenStorageRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Request.LoginRequest;
import com.kethableez.walkerapi.Request.RegisterRequest;
import com.kethableez.walkerapi.Service.UserDetailsImpl;
import com.kethableez.walkerapi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TokenStorageRepository tokenStorageRepository;

    @Autowired
    private final JwtUtils jwtUtils;


    private final String registerToken = "05da579b-cafe-4395-8eeb-88826dfd6cc9";

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Taki użytkownik nie istnieje!");
        }
        else {
            if (!user.get().getIsActive()) {
                return ResponseEntity
                        .badRequest()
                        .body("Konto nie jest aktywowane!!");
            }

            else {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
            }
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Użytkownik o takiej nazwie już istnieje");
        }

        else if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Użytkownik o takim adresie e-mail już istnieje");
        }

        else if(request.getBirthdate().isAfter(LocalDate.now())) {
            return ResponseEntity
                    .badRequest()
                    .body("Zła data urodzin!");
        }
        else {
            userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.OK).body("Zarejestrowano pomyślnie!");
        }
    }
    @PostMapping("/register/" + registerToken)
    public ResponseEntity<?>  registerAdmin(@RequestBody RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Użytkownik o takiej nazwie już istnieje");
        }

        else if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Użytkownik o takim adresie e-mail już istnieje");
        }

        else if(request.getBirthdate().isAfter(LocalDate.now())) {
            return ResponseEntity
                    .badRequest()
                    .body("Zła data urodzin!");
        }

        else {
            userService.registerAdmin(request);
            return ResponseEntity.ok("Zarejestrowano pomyślnie!");
        }
    }

    @PutMapping("/confirm/{token}")
    public ResponseEntity<?> confirmUser(@PathVariable("token") String token, @RequestBody String code){
        TokenStorage userToken = tokenStorageRepository.findByToken(token).orElseThrow();
        if (userToken.isConfirmed()) {
            return ResponseEntity
                    .badRequest()
                    .body("Token został już potwierdzony!!");
        }
        else {
            if (userToken != null) {
                if(!userToken.getCode().equals(code)) {
                    return ResponseEntity
                            .badRequest()
                            .body("Zły kod!");
                }

                else {
                    if (LocalDateTime.now().isAfter(userToken.getExpiredAt())){
                        return ResponseEntity
                                .badRequest()
                                .body("Token wygasł!");
                    }
                    else {
                        Role role = userToken.getRole();
                        switch (role){
                            case ROLE_ADMIN:
                                userService.confirmAdmin(userToken);
                                break;

                            case ROLE_OWNER:
                                userService.confirmOwner(userToken);
                                break;

                            case ROLE_SITTER:
                                userService.confirmSitter(userToken);
                                break;
                        }
                        userToken.setConfirmed(true);
                        tokenStorageRepository.save(userToken);
                        return ResponseEntity.ok("Potwierdzono pomyślnie!");
                    }
                }
            }
            else {
                return ResponseEntity
                        .badRequest()
                        .body("Zły token!");
            }
        }
    }
}
