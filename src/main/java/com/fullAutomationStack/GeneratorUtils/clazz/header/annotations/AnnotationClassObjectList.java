package com.fullAutomationStack.GeneratorUtils.clazz.header.annotations;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AnnotationClassObjectList {

    @Getter @Setter
    private List<AnnotationClassObject> annotationDeclarationObjectList;

    public AnnotationClassObjectList() {
        this.annotationDeclarationObjectList = new ArrayList<AnnotationClassObject>();
    }

    public void add(@NonNull AnnotationClassObject annotationClasObject) {
        annotationDeclarationObjectList.add(annotationClasObject);
    }
}
