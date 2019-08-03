package com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes;

import lombok.Getter;

public enum AnnotationTypes {

//  MARKER_ANNOTATION_EXPRESION: An annotation that uses only the annotation type name.    Example: @Override
//  NORMAL_ANNOTATION_EXPRESION: An annotation that has zero or more key-value pairs.      Example: @Mapping(a=5, d=10)
//  SINGLE_MEMBER_ANNOTATION_EXPRESSION: An annotation that has a single value.            Example: @Count(15)

    NORMAL_ANNOTATION_EXPRESION("NormalAnnotationExpresion"),
    MARKER_ANNOTATION_EXPRESION("MarkerAnnotationExpr"),
    SINGLE_MEMBER_ANNOTATION_EXPRESSION("SingleMemberAnnotationExpr");

    private @Getter String value;

    AnnotationTypes(String value) {
        this.value = value;
    }


}
