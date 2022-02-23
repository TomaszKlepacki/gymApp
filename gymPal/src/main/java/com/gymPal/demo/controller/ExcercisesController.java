package com.gymPal.demo.controller;

import com.gymPal.demo.model.ExcerciseModel;
import com.gymPal.demo.service.ExcerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/excercise")
@AllArgsConstructor
public class ExcercisesController {

    ExcerciseService excerciseService;

    @PostMapping("/addExcercise")
    public String addExcercise(@RequestBody ExcerciseModel excerciseModel){
        return excerciseService.addExcercise(excerciseModel);
    }

    @GetMapping("/generateExcercises")
    public String generate(){
        return excerciseService.generatePayload();
    }


}
