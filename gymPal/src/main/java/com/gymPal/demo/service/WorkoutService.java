package com.gymPal.demo.service;

import com.gymPal.demo.entity.Workout;
import com.gymPal.demo.model.NewWorkoutModel;

public interface WorkoutService {
    Workout getWorkout(String email);

    String saveNewWorkout(NewWorkoutModel workout);
}
