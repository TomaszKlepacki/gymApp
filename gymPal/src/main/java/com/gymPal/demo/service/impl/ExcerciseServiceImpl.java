package com.gymPal.demo.service.impl;

import com.gymPal.demo.entity.Excercise;
import com.gymPal.demo.enums.BodyPart;
import com.gymPal.demo.enums.ExcerciseIntesitivity;
import com.gymPal.demo.enums.ExcerciseRank;
import com.gymPal.demo.enums.WeightType;
import com.gymPal.demo.model.ExcerciseModel;
import com.gymPal.demo.repository.ExcerciseRepository;
import com.gymPal.demo.service.ExcerciseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcerciseServiceImpl implements ExcerciseService {

    private static final String EXCERCISE_EXISTS_ALREADY = "EXCERCISE_EXISTS_ALREADY";
    private static final String EXCERCISE_SAVED = "EXCERCISE_SAVED";
    ExcerciseRepository excerciseRepository;

    @Override
    public String addExcercise(ExcerciseModel excerciseModel) {
    Excercise excercise= excerciseRepository.findByExcerciseName(excerciseModel.getName());
    if (excercise!=null){
        return EXCERCISE_EXISTS_ALREADY;
    } else {
        excercise=mapExcerciseModel(excerciseModel);
        excerciseRepository.save(excercise);
    }
        return EXCERCISE_SAVED;
    }

    @Transactional
    @Override
    public String generatePayload(){
        List<Excercise> excercises= new ArrayList<>();
        Excercise e1= new Excercise("Bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN, WeightType.ATLAS);
        Excercise e2= new Excercise("Incline bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.ATLAS);
        Excercise e3= new Excercise("Decline bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.ATLAS);
        Excercise e4= new Excercise("Cable crossover",BodyPart.CHEST,ExcerciseIntesitivity.MEDIUM,ExcerciseRank.SUPPORTING,WeightType.ATLAS);
        Excercise e5= new Excercise("Barbell bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.FREE_WEIGHTS);
        Excercise e6= new Excercise("Incline barbell bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.FREE_WEIGHTS);
        Excercise e7= new Excercise("Decline barbell bench press",BodyPart.CHEST,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.FREE_WEIGHTS);
        Excercise e8= new Excercise("Barbell curl",BodyPart.BICEPS,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.ATLAS);
        Excercise e9= new Excercise("Chin up",BodyPart.BICEPS,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.FREE_WEIGHTS);
        Excercise e10= new Excercise("Preacher curl",BodyPart.BICEPS,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.FREE_WEIGHTS);
        Excercise e11= new Excercise("Hammer curl",BodyPart.BICEPS,ExcerciseIntesitivity.LOW,ExcerciseRank.ACCESSORY,WeightType.FREE_WEIGHTS);
        Excercise e12= new Excercise("Cable curl",BodyPart.BICEPS,ExcerciseIntesitivity.HIGH,ExcerciseRank.MAIN,WeightType.ATLAS);
        excercises.add(e1);
        excercises.add(e2);
        excercises.add(e3);
        excercises.add(e4);
        excercises.add(e5);
        excercises.add(e6);
        excercises.add(e7);
        excercises.add(e8);
        excercises.add(e9);
        excercises.add(e10);
        excercises.add(e11);
        excercises.add(e12);
        excerciseRepository.saveAllAndFlush(excercises);
        return "SUCCESS";
    }

    private Excercise mapExcerciseModel(ExcerciseModel excerciseModel) {
        Excercise excercise= new Excercise();
        excercise.setExcerciseName(excerciseModel.getName());
        excercise.setBodyPart(BodyPart.valueOf(excerciseModel.getBodyPart().toUpperCase()));
        excercise.setExcerciseRank(ExcerciseRank.valueOf(excerciseModel.getImportance().toUpperCase()));
        excercise.setIntesitivity(ExcerciseIntesitivity.valueOf(excerciseModel.getIntensivity().toUpperCase()));
        return excercise;
    }

}
