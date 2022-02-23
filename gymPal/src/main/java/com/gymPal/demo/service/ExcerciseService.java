package com.gymPal.demo.service;

import com.gymPal.demo.model.ExcerciseModel;

public interface ExcerciseService {
    String addExcercise(ExcerciseModel excerciseModel);

    String generatePayload();

}
