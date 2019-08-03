package com.fullAutomationStack.GeneratorUtils.clazz.header.annotations;

import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;

public class AnnotationClassObject {

    @Getter @Setter
    private String annotationName;

    @Getter
    private AnnotationTypes annotationType;

    @Getter
    private EnumSet<Modifier> annotationModifier;

    @Getter
    private List<AnnotationValuesObject> annotationValuesList;

    @Getter
    private AnnotationDeclaration annotationDeclaration;

    @Getter
    private AnnotationExpr annotationExpresion;

    public AnnotationClassObject(@NonNull String annotationName, Modifier annotationModifier, AnnotationTypes annotationType) {
        this.annotationName = annotationName;
        this.annotationType = annotationType;
        this.annotationModifier =
                TypeAndVisibilityUtils.getModificadorMetodoSegunTipo
                        (StringUtils.lowerCase(annotationModifier.toString()));

        this.annotationDeclaration = new AnnotationDeclaration();
    }

    private AnnotationExpr setAnnotationTypes(@NonNull AnnotationTypes annotationType) {
        AnnotationExpr annotationExpresion = null;

        switch (annotationType){
            case MARKER_ANNOTATION_EXPRESION:

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

                break;
        }

        return annotationExpresion;
    }

    private void add(@NonNull String key, @NonNull String value) {
        this.annotationValuesList.add(new AnnotationValuesObject(key, value, true));
    }

    private void add(@Nonnull String value){
        this.annotationValuesList.add(new AnnotationValuesObject(value, false));
    }


    private void completeAnnotation() {
        this.annotationDeclaration.setModifiers(this.annotationModifier);
        this.annotationDeclaration.setName(this.annotationName);
        this.annotationExpresion = setAnnotationTypes(this.annotationType);
        this.annotationDeclaration.addAnnotation(this.annotationExpresion);
    }
}
