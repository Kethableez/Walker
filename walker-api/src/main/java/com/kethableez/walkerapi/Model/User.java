package com.kethableez.walkerapi.Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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

    @NotBlank
    @Transient
    private Integer age;

    @NotBlank
    private String avatar;

    @NotBlank
    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles = new HashSet<>();

    @NotBlank
    private Boolean isActive;

    @NotBlank
    private Boolean isSubscribed;

    public User(String username, String email, String password, String firstName, String lastName, LocalDate birthdate, String avatar, Gender gender, Boolean isActive, Boolean isSubscribed) {
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

    public Integer getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
