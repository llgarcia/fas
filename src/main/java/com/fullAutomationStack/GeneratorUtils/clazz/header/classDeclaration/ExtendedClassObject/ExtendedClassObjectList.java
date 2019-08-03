package com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ExtendedClassObjectList {

    @Getter
    private List<ExtendedClassObject> extendedClassObjectList;

    public ExtendedClassObjectList() {
        this.extendedClassObjectList = new ArrayList<ExtendedClassObject>();
    }

    public void add(ExtendedClassObject extendedClassObject){
        extendedClassObjectList.add(extendedClassObject);
    }

    public boolean existingExtends() {
        return extendedClassObjectList != null && extendedClassObjectList.size() != 0;
    }
}
