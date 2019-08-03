package com.fullAutomationStack.GeneratorUtils.clazz.body.annotation;

import com.fullAutomationStack.GeneratorUtils.clazz.header.annotations.AnnotationClassObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AnnotationMethodObjectList {

    @Getter
    @Setter
    private List<AnnotationMethodObject> annotationDeclarationObjectList;

    public AnnotationMethodObjectList() {
        this.annotationDeclarationObjectList = new ArrayList<AnnotationMethodObject>();
    }

    public void add(@NonNull AnnotationMethodObject annotationClasObject) {
        annotationDeclarationObjectList.add(annotationClasObject);
    }

    public boolean existingAnnotations() {
        return annotationDeclarationObjectList != null && annotationDeclarationObjectList.size() != 0;
    }
}
