package com.gymPal.demo.service.impl;

import com.gymPal.demo.entity.Excercise;
import com.gymPal.demo.entity.User;
import com.gymPal.demo.entity.Workout;
import com.gymPal.demo.enums.BodyPart;
import com.gymPal.demo.enums.ExcerciseIntesitivity;
import com.gymPal.demo.enums.ExcerciseRank;
import com.gymPal.demo.enums.WeightType;
import com.gymPal.demo.model.NewWorkoutExcerciseModel;
import com.gymPal.demo.model.NewWorkoutModel;
import com.gymPal.demo.repository.ExcerciseRepository;
import com.gymPal.demo.repository.UserRepository;
import com.gymPal.demo.repository.WorkoutRepository;
import com.gymPal.demo.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    WorkoutRepository workoutRepository;
    ExcerciseRepository excerciseRepository;
    UserRepository userRepository;

    @Override
    public Workout getWorkout(String email) {
        User user = userRepository.findByEmail(email);
        Workout workout = new Workout();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        List<Excercise> excercises = new ArrayList<>();
        excercises.add(new Excercise("Bench press", BodyPart.CHEST, ExcerciseIntesitivity.HIGH, ExcerciseRank.MAIN, WeightType.ATLAS));
        excercises.add(new Excercise("Cable curl", BodyPart.BICEPS, ExcerciseIntesitivity.HIGH, ExcerciseRank.MAIN, WeightType.ATLAS));
        workout.setUserSet(userSet);
        workout.setExcercises(excercises);
        workoutRepository.save(workout);
        return workout;
    }

    @Override
    public String saveNewWorkout(NewWorkoutModel workoutModel) {
        User user = userRepository.findByEmail(workoutModel.getEmail());
        if (user == null) {
            return null; //TODO
        } else {
            Workout workout = mapWorkoutModelToWorkout(workoutModel, user);
            workoutRepository.save(workout);
        }
        return "saved";
    }

    private Workout mapWorkoutModelToWorkout(NewWorkoutModel workoutModel, User user) {
        Workout workout = new Workout();
        Set<User> userSet = new HashSet<>();
        List<Excercise> excercises = new ArrayList<>();
        List<NewWorkoutExcerciseModel> excerciseList = workoutModel.getExcerciseList();

        for (NewWorkoutExcerciseModel newWorkoutExcerciseModel : excerciseList) {
            Excercise excercise = excerciseRepository.findByExcerciseName(newWorkoutExcerciseModel.getExcerciseName());
            excercises.add(excercise);
        }
        workout.setExcercises(excercises);
        workout.setUserSet(userSet);
        return workout;
    }
}
