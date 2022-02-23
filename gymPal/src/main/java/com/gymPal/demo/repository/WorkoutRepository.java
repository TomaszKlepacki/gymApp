package com.gymPal.demo.repository;

import com.gymPal.demo.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Long> {
}
