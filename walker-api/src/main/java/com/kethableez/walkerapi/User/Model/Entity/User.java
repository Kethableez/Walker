package com.kethableez.walkerapi.User.Model.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kethableez.walkerapi.Utility.Enum.Gender;
import com.kethableez.walkerapi.Utility.Enum.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotBlank
    private LocalDate birthdate;

    private String zipCode;

    private String city;

    private String districtCode;
    
    private String regionCode;

    private String description;

    @NotBlank
    private String avatar;

    @NotBlank
    @Size(max = 20)
    private Gender gender;

    @DBRef
    private Set<UserRole> roles = new HashSet<>();

    @NotBlank
    private Boolean isActive;

    @NotBlank
    private Boolean isSubscribed;

    private Boolean isBlocked;

    private Boolean isBanned;

    public User(
        String username, 
        String email, 
        String password, 
        String firstName, 
        String lastName, 
        LocalDate birthdate,
        String zipCode,
        String city,
        String districtCode,
        String regionCode, 
        String avatar, 
        Gender gender, 
        Boolean isActive, 
        Boolean isSubscribed, 
        Boolean isBlocked, 
        Boolean isBanned) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.zipCode = zipCode;
            this.city = city;
            this.districtCode = districtCode;
            this.regionCode = regionCode;
            this.avatar = avatar;
            this.gender = gender;
            this.isActive = isActive;
            this.isSubscribed = isSubscribed;
            this.isBlocked = isBlocked;
            this.isBanned = isBanned;
    }

    public String getAvatar() {
        return "http://localhost:8080/image/user/" + this.id + "/" + this.avatar;
    }

    public Set<Role> getRoles() {
        return this.roles.stream().map(role -> role.getRole()).collect(Collectors.toSet());
    }

    public Set<UserRole> getUserRoles() {
        return this.roles;
    }
}