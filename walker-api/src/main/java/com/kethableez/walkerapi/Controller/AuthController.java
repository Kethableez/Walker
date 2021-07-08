package com.kethableez.walkerapi.Controller;

import com.kethableez.walkerapi.Config.Jwt.JwtResponse;
import com.kethableez.walkerapi.Config.Jwt.JwtUtils;
import com.kethableez.walkerapi.Model.TokenStorage;
import com.kethableez.walkerapi.Repository.TokenStorageRepository;
import com.kethableez.walkerapi.Repository.UserRepository;
import com.kethableez.walkerapi.Request.LoginUser;
import com.kethableez.walkerapi.Request.RegisterRequest;
import com.kethableez.walkerapi.Service.UserDetailsImpl;
import com.kethableez.walkerapi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    public ResponseEntity<?> login(@RequestBody LoginUser request){

        if (!userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Taki użytkownik nie istnieje!");
        }

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
            return ResponseEntity.ok("Zarejestrowano pomyślnie!");
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
    public ResponseEntity<?> confirmAdmin(@PathVariable("token") String token, @RequestBody String code){
        TokenStorage adminToken = tokenStorageRepository.findByToken(token).orElseThrow();

        if (adminToken != null) {
            if(!adminToken.getCode().equals(code)) {
                return ResponseEntity
                        .badRequest()
                        .body("Zły kod!");
            }

            else {
                if (LocalDateTime.now().isAfter(adminToken.getExpiredAt())){
                    return ResponseEntity
                            .badRequest()
                            .body("Token wygasł!");
                }
                else {
                    userService.confirmAdmin(adminToken);
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
