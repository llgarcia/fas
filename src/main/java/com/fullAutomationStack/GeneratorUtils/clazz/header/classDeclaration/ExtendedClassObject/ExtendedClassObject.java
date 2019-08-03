package com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject;

import lombok.Getter;

public class ExtendedClassObject {

    @Getter
    private String nameClass;

    public ExtendedClassObject(String nameClassExtended){
        this.nameClass = nameClassExtended;
    }

}
