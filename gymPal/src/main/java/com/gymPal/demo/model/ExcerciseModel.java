package com.gymPal.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExcerciseModel {

    private String name;
    private String bodyPart;
    private String intensivity;
    private String importance;
}
