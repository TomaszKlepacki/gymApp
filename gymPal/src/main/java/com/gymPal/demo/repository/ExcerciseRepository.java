package com.gymPal.demo.repository;

import com.gymPal.demo.entity.Excercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcerciseRepository  extends JpaRepository<Excercise,Long> {
    Excercise findByExcerciseName(String name);
}
