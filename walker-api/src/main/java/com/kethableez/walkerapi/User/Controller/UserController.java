package com.kethableez.walkerapi.User.Controller;

import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Config.Security.PasswordEncoder;
import com.kethableez.walkerapi.Owner.Service.OwnerService;
import com.kethableez.walkerapi.Sitter.Service.SitterService;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Model.Entity.UserRole;
import com.kethableez.walkerapi.User.Model.Request.UserDataRequest;
import com.kethableez.walkerapi.User.Model.Request.UserPasswordRequest;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final SitterService sitterService;

    @Autowired
    private final OwnerService ownerService;

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
         Optional<UserRole> mainRole = userService.getRole(user);
         if (mainRole.isPresent()) {
             switch(mainRole.get().getRole()){
                case ROLE_OWNER:
                    return new ResponseEntity<>(ownerService.getData(userService.getIdFromToken(token)), HttpStatus.OK);

                case ROLE_SITTER:
                    return new ResponseEntity<>(sitterService.getData(userService.getIdFromToken(token)), HttpStatus.OK);

                default:
                    return new ResponseEntity<>(user, HttpStatus.OK);
             }
         }
         else return ResponseEntity.badRequest().body(new MessageResponse("jesteś nikim"));
    }

    @GetMapping("/get_data/{username}")
    public ResponseEntity<?> getData(@PathVariable String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
         Optional<UserRole> mainRole = userService.getRole(user);
         if (mainRole.isPresent()) {
             switch(mainRole.get().getRole()){
                case ROLE_OWNER:
                    return new ResponseEntity<>(ownerService.getData(user.getId()), HttpStatus.OK);

                case ROLE_SITTER:
                    return new ResponseEntity<>(sitterService.getData(user.getId()), HttpStatus.OK);

                default:
                    return new ResponseEntity<>(user, HttpStatus.OK);
             }
         }
         else return ResponseEntity.badRequest().body(new MessageResponse("jesteś nikim"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("id") String userId) {
        UserInfo userInfo = this.userService.getUserInfo(userId);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PutMapping("/change_data")
    public ResponseEntity<?> changeData(UsernamePasswordAuthenticationToken token,
            @RequestBody UserDataRequest request) {
        ActionResponse response = this.userService.changeData(userService.getIdFromToken(token), request);

        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PutMapping("/change_password")
    public ResponseEntity<?> changePassword(UsernamePasswordAuthenticationToken token,
            @RequestBody UserPasswordRequest request) {
        User user = userRepository.findByUsername(token.getName()).get();

        if (encoder.bCryptPasswordEncoder().matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.bCryptPasswordEncoder().encode(request.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Zmieniono dane pomyślnie"));
        } else {
            return ResponseEntity.badRequest().body("Złe hasło");
        }
    }
}
