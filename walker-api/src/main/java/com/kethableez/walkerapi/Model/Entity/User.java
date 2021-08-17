package com.kethableez.walkerapi.Model.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kethableez.walkerapi.Model.Enum.Gender;

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

    // @NotBlank
    // // @Transient
    // private Integer age;

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

    public User(String username, String email, String password, String firstName, String lastName, LocalDate birthdate,
            String avatar, Gender gender, Boolean isActive, Boolean isSubscribed) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.avatar = avatar;
        this.gender = gender;
        this.isActive = isActive;
        this.isSubscribed = isSubscribed;
    }

    public String getAvatar() {
        return "http://localhost:8080/image/user/" + this.id + "/" + this.avatar;
    }
}