package com.kethableez.walkerapi.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "sitters")
@Setter
@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sitter {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sitter_walks",
    joinColumns = @JoinColumn(name = "sitter_id"),
    inverseJoinColumns = @JoinColumn(name = "walk_id"))
    private Set<Walk> walks = new HashSet<>();

    public Sitter(User user) {
        this.user = user;
    }
}
