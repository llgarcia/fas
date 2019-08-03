package com.fullAutomationStack.GeneratorUtils.clazz;


import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObject;
import lombok.Builder;

@Deprecated
@Builder(builderMethodName = "with", buildMethodName = "create")
public class SimpleHeaderClass {

    public String className;
    public String[] modifiers;
    public ExtendedClassObject[] extendedClassObject;

}
