package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GlobalEntitiesForJavaComponents<Obj> {

    public ModifiersObject modifiersObject = new ModifiersObject();
    public AnnotationMethodObjectList annotationMethodObjectList = new AnnotationMethodObjectList();

    public class ModifiersObject {
        @Getter
        private List<String> modifiers;

        public ModifiersObject () { this.modifiers = new ArrayList<String>(); }

        public boolean existAndContains() {
            return modifiers != null && modifiers.size() >=1;
        }
        public void add(String value) {
            this.modifiers.add(value);
        }
    }

    protected void createModifiersIfNoExist() {
        this.modifiersObject = new ModifiersObject();
    }

    public Obj returnSpecificType(String a) {
        modifiersObject.add(a);
        return (Obj) this;
    }

    public Obj VOID() {
        modifiersObject.add("void");
        return (Obj) this;
    }

    public Obj STRING() {
        modifiersObject.add("String");
        return (Obj) this;
    }


    public Obj STATIC() {
        modifiersObject.add("static");
        return (Obj) this;
    }

    public Obj FINAL() {
        modifiersObject.add("final");
        return (Obj) this;
    }

    public Obj SYNCHRONIZED() {
        modifiersObject.add("synchronized");
        return (Obj) this;
    }

    public Obj ABSTRACT() {
        modifiersObject.add("abstract");
        return (Obj) this;
    }


    public Obj PUBLIC() {
        modifiersObject.add("public");
        return (Obj) this;
    }


    public Obj PRIVATE() {
        modifiersObject.add("private");
        return (Obj) this;
    }


    public Obj PROTECTED() {
        modifiersObject.add("protected");
        return (Obj) this;
    }

    public Obj writeAnnotation(String annotationName, String key, String value) {
        AnnotationMethodObject o = new AnnotationMethodObject(annotationName, null, AnnotationTypes.NORMAL_ANNOTATION_EXPRESION);
        o.add(key, value);
        o = o.generateAll();
        annotationMethodObjectList.add(o);
        return (Obj) this;
    }

    protected Obj writeAnnotation(String annotationName) {
        AnnotationMethodObject o = new AnnotationMethodObject(annotationName, null, AnnotationTypes.MARKER_ANNOTATION_EXPRESION);
        o = o.generateAll();
        annotationMethodObjectList.add(o);
        return (Obj) this;
    }

    protected Obj writeAnnotationSingle(String annotationName) {
        AnnotationMethodObject o = new AnnotationMethodObject(annotationName, null, AnnotationTypes.SINGLE_MEMBER_ANNOTATION_EXPRESSION);
        o = o.generateAll();
        annotationMethodObjectList.add(o);
        return (Obj) this;
    }


}
