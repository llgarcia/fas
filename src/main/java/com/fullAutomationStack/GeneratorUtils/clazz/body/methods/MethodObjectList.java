package com.fullAutomationStack.GeneratorUtils.clazz.body.methods;

import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Method;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MethodObjectList {

    @Getter
    public List<MethodObject> listMethods;

    @Getter
    public List<Method> listMethods1;


    public MethodObjectList() {
        this.listMethods = new ArrayList<MethodObject>();
        this.listMethods1 = new ArrayList<Method>();
    }

    public void add(@NonNull MethodObject mo){
        this.listMethods.add(mo);
    }

    public void add(@NonNull Method mo){
        this.listMethods1.add(mo);
    }

    public boolean existingMethods() {
        return listMethods != null && listMethods.size() != 0;
    }
    public boolean existingMethods1() {
        return listMethods1 != null && listMethods1.size() != 0;
    }
}
