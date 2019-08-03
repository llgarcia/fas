package com.fullAutomationStack.GeneratorUtils.clazz.body.variable;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;


public class VariableObject {

    @Getter
    private VariableDeclarator variableDeclarator;

    @Getter
    private FieldDeclaration fieldDeclaration;

    /** Assigna una variable con:
    * @param name
     * @param variableType Variable type.
     * @param initzializer Init the variable with assignation to String or a method, for example.
     * @param types Visibility and acces variable type. */
    public VariableObject (@NonNull String name, @NonNull String variableType, String initzializer, @NonNull String...types) {
        this.variableDeclarator = new VariableDeclarator();

        if(ArrayUtils.contains(types, "final"))
            name = StringUtils.upperCase(name);

        this.variableDeclarator.setName(name);
        this.variableDeclarator.setType(variableType);

        if(initzializer != null)
            this.variableDeclarator.setInitializer(initzializer);

        this.fieldDeclaration = new FieldDeclaration();
//        f.addAnnotation(annotationMethodObject.getAnnotationExpresion()); // TODO
        this.fieldDeclaration.addVariable(this.variableDeclarator);
        this.fieldDeclaration.setModifiers(TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(types));
    }

    public void setInitialazer(String initialazer) {
        this.variableDeclarator.setInitializer(initialazer);
    }

    public void addAnnotations(AnnotationExpr annotation) {
        this.fieldDeclaration.addAnnotation(annotation);
    }

}
