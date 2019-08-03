package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import lombok.Builder;

import java.util.List;

@Deprecated
@Builder(builderMethodName = "with", buildMethodName = "create")
public class SimpleMethodGeneration {


    public String name;
    public String content;
    public String[] modifiers;
    public String returnType;
    public List<AnnotationMethodObject> annotationMethodObjectList;


}
