package com.gymPal.demo.controller;

import com.gymPal.demo.entity.Workout;
import com.gymPal.demo.model.NewWorkoutModel;
import com.gymPal.demo.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
@AllArgsConstructor
public class WorkoutController {

    WorkoutService workoutService;
    @GetMapping("/getWorkout")
    public Workout getWorkout(){
        return workoutService.getWorkout("kp@gmail.com");
    }

    @PostMapping("/saveWorkout")
    public String saveWorkout(@RequestBody NewWorkoutModel workout){
        return workoutService.saveNewWorkout(workout);
    }
}
