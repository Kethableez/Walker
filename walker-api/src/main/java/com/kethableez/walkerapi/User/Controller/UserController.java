package com.kethableez.walkerapi.User.Controller;

import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Model.Request.DescriptionRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get_data")
    public ResponseEntity<?> getData(UsernamePasswordAuthenticationToken token) {
        Optional<User> user = userRepository.findByUsername(token.getName());
        // Optional<UserRole> mainRole = userService.getRole(user);
         if (user.isPresent()) {
            UserCard userCard = userService.getUserCard(user.get().getId());
            return new ResponseEntity<>(userCard, HttpStatus.OK);
         }
         else return ResponseEntity.badRequest().body(new MessageResponse("jesteś nikim"));
    }

    @GetMapping("/get_data/{username}")
    public ResponseEntity<?> getData(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserCard userCard = userService.getUserCard(user.get().getId());
            return new ResponseEntity<>(userCard, HttpStatus.OK);
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
                ActionResponse response = this.userService.changePassword(userService.getIdFromToken(token), request);
                if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
                else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PutMapping("/change_description")
    public ResponseEntity<?> changeDescription(UsernamePasswordAuthenticationToken token, @RequestBody DescriptionRequest request) {
        ActionResponse response = this.userService.changeDescription(userService.getIdFromToken(token), request);
        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PutMapping("/change_avatar")
    public ResponseEntity<?> changeAvatar(UsernamePasswordAuthenticationToken token, @RequestParam("imageFile") MultipartFile imageFile) {
        ActionResponse response = this.userService.changeAvatar(userService.getIdFromToken(token), imageFile);
        if (response.isSuccess()) return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else return ResponseEntity.badRequest().body(response.getMessage());
    }
}
