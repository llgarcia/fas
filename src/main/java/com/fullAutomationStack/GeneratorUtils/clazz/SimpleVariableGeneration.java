package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import lombok.Builder;

import java.util.List;

@Deprecated
@Builder(builderMethodName = "with", buildMethodName = "create")
public class SimpleVariableGeneration {//extends Core_SimpleGenerationClass{

    public String name;
    public String content;
    public String[] modifiers;
    public String returnType;
    public List<AnnotationMethodObject> annotationMethodObjectList;

//    public SimpleVariableGeneration(){}

//    public SimpleVariableGeneration withAnnotation(String variable) {
//
//        return this;
//    }
//
//    public SimpleVariableGeneration withContent(String content) {
//
//        return this;
//    }

}
