package com.kethableez.walkerapi.User.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.kethableez.walkerapi.Config.Security.PasswordEncoder;
import com.kethableez.walkerapi.Image.Service.ImageService;
import com.kethableez.walkerapi.User.Model.DTO.UserCard;
import com.kethableez.walkerapi.User.Model.DTO.UserInfo;
import com.kethableez.walkerapi.User.Model.Entity.TokenStorage;
import com.kethableez.walkerapi.User.Model.Entity.User;
import com.kethableez.walkerapi.User.Model.Entity.UserRole;
import com.kethableez.walkerapi.User.Model.Request.DescriptionRequest;
import com.kethableez.walkerapi.User.Model.Request.RegisterRequest;
import com.kethableez.walkerapi.User.Model.Request.UserDataRequest;
import com.kethableez.walkerapi.User.Model.Request.UserPasswordRequest;
import com.kethableez.walkerapi.User.Repository.TokenStorageRepository;
import com.kethableez.walkerapi.User.Repository.UserRepository;
import com.kethableez.walkerapi.User.Repository.UserRoleRepository;
import com.kethableez.walkerapi.Utility.Enum.Role;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;
import com.kethableez.walkerapi.Utility.Model.ActivityType;
import com.kethableez.walkerapi.Utility.Model.Notification;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Services.ActivityService;
import com.kethableez.walkerapi.Utility.Services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

        private final UserRepository userRepository;
        private final UserRoleRepository roleRepository;
        private final TokenStorageRepository tokenStorageRepository;
        private final PasswordEncoder encoder;
        private final MapperService mapper;
        private final ImageService imageService;
        private final ActivityService activityService;
        private final NotificationService notificationService;

        @Autowired
        public UserService(
                UserRepository userRepository, 
                UserRoleRepository roleRepository,
                TokenStorageRepository tokenStorageRepository, 
                PasswordEncoder encoder, 
                MapperService mapper,
                ImageService imageService, 
                ActivityService activityService,
                NotificationService notificationService) {
                this.userRepository = userRepository;
                this.roleRepository = roleRepository;
                this.tokenStorageRepository = tokenStorageRepository;
                this.encoder = encoder;
                this.mapper = mapper;
                this.imageService = imageService;
                this.activityService = activityService;
                this.notificationService = notificationService;
        }

        public String registerUser(RegisterRequest request) {
                Random rnd = new Random();
                int code = rnd.nextInt(999999);

                User newUser = new User(request.getUsername(), request.getEmail(),
                                encoder.bCryptPasswordEncoder().encode(request.getPassword()), request.getFirstName(),
                                request.getLastName(), request.getBirthdate(), request.getCity(), request.getAvatar(),
                                request.getGender(), true, request.getIsSubscribed(), false, false);
                Set<UserRole> roles = new HashSet<>();
                Role role = (request.getRole().equals(Role.ROLE_OWNER)) ? Role.ROLE_OWNER : Role.ROLE_SITTER;
                roles.add(roleRepository.findByRole(Role.ROLE_USER).orElseThrow());
                roles.add(roleRepository.findByRole(role).orElseThrow());
                newUser.setRoles(roles);

                String token = UUID.randomUUID().toString();

                TokenStorage userToken = new TokenStorage(request.getUsername(), role, token,
                                String.format("%06d", code), LocalDateTime.now(), LocalDateTime.now().plusMinutes(20),
                                false);

                tokenStorageRepository.save(userToken);
                userRepository.save(newUser);
                this.activityService.reportActivity(newUser.getId(), ActivityType.REGISTER);
                return newUser.getId();
        }

        public String registerAdmin(RegisterRequest request) {
                Random rnd = new Random();
                int code = rnd.nextInt(999999);
                User newUser = new User(request.getUsername(), request.getEmail(),
                                encoder.bCryptPasswordEncoder().encode(request.getPassword()), request.getFirstName(),
                                request.getLastName(), request.getBirthdate(), request.getCity(), request.getAvatar(),
                                request.getGender(), true, request.getIsSubscribed(), false, false);
                Set<UserRole> roles = new HashSet<>();
                roles.add(roleRepository.findByRole(request.getRole()).orElseThrow());
                roles.add(roleRepository.findByRole(Role.ROLE_ADMIN).orElseThrow());
                newUser.setRoles(roles);

                TokenStorage token = new TokenStorage(request.getUsername(), Role.ROLE_ADMIN,
                                UUID.randomUUID().toString(), String.format("%06d", code), LocalDateTime.now(),
                                LocalDateTime.now().plusMinutes(20), false);

                tokenStorageRepository.save(token);
                userRepository.save(newUser);
                this.activityService.reportActivity(newUser.getId(), ActivityType.REGISTER);
                return newUser.getId();
        }

        public ActionResponse confirmUser(String token, String code) {
                Optional<TokenStorage> userToken = tokenStorageRepository.findByToken(token);

                if (userToken.isPresent()) {
                        if (!userToken.get().isConfirmed()) {
                                if (userToken.get().getCode().equals(code)) {
                                        if (LocalDateTime.now().isBefore(userToken.get().getExpiredAt())) {
                                                switch (userToken.get().getRole()) {
                                                case ROLE_ADMIN:
                                                        confirmAdmin(userToken.get());
                                                        break;
                                                case ROLE_OWNER:
                                                        confirmOwner(userToken.get());
                                                        break;
                                                case ROLE_SITTER:
                                                        confirmSitter(userToken.get());
                                                        break;
                                                default:
                                                        return new ActionResponse(false, "Error!");
                                                }
                                                userToken.get().setConfirmed(true);
                                                tokenStorageRepository.save(userToken.get());
                                                return new ActionResponse(true, "Konto zostało potwierdzone");
                                        } else
                                                return new ActionResponse(false, "Token wygasł");
                                } else
                                        return new ActionResponse(false, "Zły kod");
                        } else
                                return new ActionResponse(false, "Token został potwierdzony");
                } else
                        return new ActionResponse(false, "Zły token");
        }

        public User confirmAdmin(TokenStorage token) {
                User admin = userRepository.findByUsername(token.getUsername()).orElseThrow();
                Set<UserRole> roles = admin.getUserRoles();
                roles.add(roleRepository.findByRole(Role.ROLE_ADMIN).orElseThrow());
                admin.setRoles(roles);
                admin.setIsActive(true);
                return userRepository.save(admin);
        }

        public User confirmOwner(TokenStorage token) {
                User owner = userRepository.findByUsername(token.getUsername()).orElseThrow();
                Set<UserRole> roles = owner.getUserRoles();
                roles.add(roleRepository.findByRole(Role.ROLE_OWNER).orElseThrow());
                owner.setRoles(roles);
                owner.setIsActive(true);

                return userRepository.save(owner);
        }

        public User confirmSitter(TokenStorage token) {
                User sitter = userRepository.findByUsername(token.getUsername()).orElseThrow();
                Set<UserRole> roles = sitter.getUserRoles();
                roles.add(roleRepository.findByRole(Role.ROLE_SITTER).orElseThrow());
                sitter.setRoles(roles);
                sitter.setIsActive(true);
                return userRepository.save(sitter);
        }

        // TODO: WALIDACJE!!!!
        public ActionResponse changeData(String userId, UserDataRequest request) {
                if (userRepository.findById(userId).isPresent()) {
                        User user = userRepository.findById(userId).get();
                        user.setFirstName(request.getFirstName());
                        user.setLastName(request.getLastName());
                        user.setBirthdate(request.getBirthdate());
                        user.setIsSubscribed(request.getIsSubscribed());
                        this.userRepository.save(user);
                        return new ActionResponse(true, "Zmieniono dane.");

                } else
                        return new ActionResponse(false, "Nie znaleziono takiego użytkownika");

        }

        public ActionResponse changePassword(String userId, UserPasswordRequest request) {
                if (userRepository.findById(userId).isPresent()) {
                        User user = userRepository.findById(userId).get();
                        if (encoder.bCryptPasswordEncoder().matches(request.getOldPassword(), user.getPassword())) {
                                user.setPassword(encoder.bCryptPasswordEncoder().encode(request.getNewPassword()));
                                userRepository.save(user);
                                return new ActionResponse(true, "Zmieniono hasło,");
                        } else
                                return new ActionResponse(false, "Złe hasło.");
                } else
                        return new ActionResponse(false, "Nie znaleziono takiego użytkownika");
        }

        public ActionResponse changeDescription(String userId, DescriptionRequest request) {
                if (userRepository.findById(userId).isPresent()) {
                        User user = userRepository.findById(userId).get();
                        user.setDescription(request.getDescription());
                        userRepository.save(user);
                        return new ActionResponse(true, "Zmieniono opis,");
                } else
                        return new ActionResponse(false, "Wystapił problem.");
        }

        public ActionResponse changeAvatar(String userId, MultipartFile file) {
                try {
                        return this.imageService.changeUserPhoto(file, userId);
                } catch (IOException e) {
                        e.printStackTrace();
                        return new ActionResponse(false, "Wystąpił error");
                }

        }

        public List<Notification> getUserNotifications(String userId) {
                return this.notificationService.getUserNotifications(userId);
        }

        public void markAsRead(String notificationId) {
                this.notificationService.markAsRead(notificationId);
        }

        public String getIdFromToken(UsernamePasswordAuthenticationToken token) {
                return ((UserDetailsImpl) token.getPrincipal()).getId();
        }

        public String getIdFromUsername(String username) {
                return userRepository.findByUsername(username).get().getId();
        }

        public UserCard getUserCard(String userId) {
                return mapper.userCardMapper(userId);
        }

        public UserInfo getUserInfo(String userId) {
                return mapper.userInfoMapper(userId);
        }

        public Role getUserRole(UserCard user) {
                return mapper.getMainRole(user.getRoles());
        }

}
