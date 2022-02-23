package com.gymPal.demo.enums;

public enum BodyPart {
    CHEST("chest"),
    SHOULDERS("shoulders"),
    BICEPS("biceps"),
    TRICEPS("triceps"),
    FOREARMS("forearms"),
    BACK("back"),
    QUADS("quads"),
    GLUTES("glutes"),
    BICEPS_FEMORIS("biceps_femoris"),
    CALVES("calves");

    private String name;

    BodyPart(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
