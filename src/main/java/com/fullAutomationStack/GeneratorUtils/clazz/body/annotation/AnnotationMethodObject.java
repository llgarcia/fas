package com.fullAutomationStack.GeneratorUtils.clazz.body.annotation;

import com.fullAutomationStack.GeneratorUtils.clazz.header.annotations.AnnotationValuesObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.expr.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class AnnotationMethodObject {

    @Getter
    @Setter
    private String annotationName;

    @Getter
    private AnnotationTypes annotationType;

    @Getter
    private EnumSet<Modifier> annotationModifier;

    @Getter @Setter
    private List<AnnotationValuesObject> annotationValuesList;

    @Getter
    private AnnotationDeclaration annotationDeclaration;

    @Getter
    private AnnotationExpr annotationExpresion;

    public AnnotationMethodObject(@NonNull String annotationName, Modifier annotationModifier, AnnotationTypes annotationType) {
        this.annotationName = annotationName;
        this.annotationType = annotationType;
        if(annotationModifier != null)
            this.annotationModifier =
                TypeAndVisibilityUtils.getModificadorMetodoSegunTipo
                        (StringUtils.lowerCase(annotationModifier.asString()));

        this.annotationDeclaration = new AnnotationDeclaration();
        this.annotationValuesList = new ArrayList<AnnotationValuesObject>();
    }

    private AnnotationExpr setAnnotationTypes(@NonNull AnnotationTypes annotationType) {
        AnnotationExpr annotationExpresion = null;

        switch (annotationType){
            case MARKER_ANNOTATION_EXPRESION:
                MarkerAnnotationExpr finalMarkerAnnotationExpression = new MarkerAnnotationExpr();
                finalMarkerAnnotationExpression.setName(new Name(annotationName));

                annotationExpresion = finalMarkerAnnotationExpression;

                break;

            case NORMAL_ANNOTATION_EXPRESION:

                // todo revisar si funciona el "parser" de 'NormalExpresion' a 'annotationExpresion'
//                annotationExpresion = new NormalAnnotationExpr();
//                annotationExpresion.setName(new Name(annotationName));
//                annotationExpresion = (NormalAnnotationExpr) annotationExpresion;

                NormalAnnotationExpr finalAnnotationExpresion = new NormalAnnotationExpr();
                finalAnnotationExpresion.setName(new Name(annotationName));

                annotationValuesList.forEach(annotationValuesObject ->
                        finalAnnotationExpresion.addPair(annotationValuesObject.getKey(), annotationValuesObject.getValue()));

                annotationExpresion = finalAnnotationExpresion;

                break;

            case SINGLE_MEMBER_ANNOTATION_EXPRESSION:
                SingleMemberAnnotationExpr finalSingleAnnotationExpression = new SingleMemberAnnotationExpr();
                finalSingleAnnotationExpression.setName(new Name(annotationName));

                annotationExpresion = finalSingleAnnotationExpression;

                break;
        }

        return annotationExpresion;
    }

    public void add(@NonNull String key, @NonNull String value) {
        this.annotationValuesList.add(new AnnotationValuesObject(key, value, true));
    }

    private void add(@NonNull String value){
        this.annotationValuesList.add(new AnnotationValuesObject(value, false));
    }

//    public void addAnnotations() {
//        NormalAnnotationExpr finalAnnotationExpresion = new NormalAnnotationExpr();
//        annotationValuesList.forEach(annotationValuesObject ->
//                finalAnnotationExpresion.addPair(annotationValuesObject.getKey(), annotationValuesObject.getValue()));
//    }

    public AnnotationMethodObject generateAll() {
        // TODO el 'modifier' y el 'name' sirven para anotaciones de clases???
//        this.annotationDeclaration.setModifiers(this.annotationModifier);
//        this.annotationDeclaration.setName(this.annotationName);
        this.annotationExpresion = setAnnotationTypes(this.annotationType);
        this.annotationDeclaration.addAnnotation(this.annotationExpresion);
        return this;
    }
}
