package com.fullAutomationStack.GeneratorUtils.clazz.body.variable;

import com.fullAutomationStack.GeneratorUtils.clazz.Core_SimpleGenerationClass;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Variable;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class VariableObjectList {

    @Getter
    public List<VariableObject> listVariables;

    @Getter List<Variable> listVariables1;

    public VariableObjectList() {
        this.listVariables = new ArrayList<VariableObject>();
        this.listVariables1 = new ArrayList<Variable>();
    }

    public void add(@NonNull VariableObject mo){
        this.listVariables.add(mo);
    }
    public void add(@NonNull Variable mo){
        this.listVariables1.add(mo);
    }

    public boolean existingVariables() {
        return listVariables != null && listVariables.size() != 0;
    }

    public boolean existingVariables1() {
        return listVariables1 != null && listVariables1.size() != 0;
    }

    public VariableObject getLastVariable() {
        return listVariables.get(listVariables.size()-1);
    }
}
