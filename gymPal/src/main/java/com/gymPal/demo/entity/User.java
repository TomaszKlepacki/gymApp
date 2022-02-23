package com.gymPal.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    @Column(length = 60)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();

    private boolean isEnabled=false;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable( name = "workoutId",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private Set<Workout> savedWorkouts;

    public User(String username, String email, String password, boolean isEnabled, Set<Workout> savedWorkouts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.savedWorkouts = savedWorkouts;
    }
}
