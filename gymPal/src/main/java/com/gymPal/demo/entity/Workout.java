package com.gymPal.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Excercise> excercises;
    @ManyToMany(mappedBy = "savedWorkouts")
    private Set<User> userSet;
}
