package com.fullAutomationStack.GeneratorUtils.clazz.body.methods;

import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.parameters.ParametroJavaParser;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.EnumSet;
import java.util.List;

public class MethodObject {

    @Getter
    private MethodDeclaration methodDeclaration;

    @Getter
    private VariableDeclarator variableDeclaration;

    public MethodObject () {
        this.methodDeclaration = new MethodDeclaration();
    }

    public MethodObject (String name, String returnType, @NonNull String...types) {
        this.methodDeclaration = new MethodDeclaration();
        this.methodDeclaration.setName(new SimpleName(name));
        this.methodDeclaration.setType(returnType);
        this.methodDeclaration.setModifiers(TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(types));
    }

    public void setName(String name){
        this.methodDeclaration.setName(new SimpleName(name));
    }

    public void setModifiers(String...modifiers){
        this.methodDeclaration.setModifiers(
                TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(modifiers));
    }

    public void setModifiers(String modifier){
        this.methodDeclaration.setModifiers(
                TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(modifier));
    }

    public void setReturnType(String returnType){
        this.methodDeclaration.setType(
                TypeAndVisibilityUtils.getTypeParameterBy(returnType));
    }

    public void addParameter(String classTypeArgument, String nameArgument){
        this.methodDeclaration.addParameter(classTypeArgument, nameArgument);
    }

    public void setMethodThrows(Class<? extends Exception>...trows) {
        for(Class<? extends Exception> e : trows)
            this.methodDeclaration.addThrownException(e);
    }

    // todo revisar si va con los 3 tipos de Annotaciones
    public void addAnnotation(AnnotationMethodObject annotationMethodObject){
        this.methodDeclaration.addAnnotation(annotationMethodObject.getAnnotationExpresion());
    }

    public void addAnnotation(AnnotationExpr annotationMethodObject){
        this.methodDeclaration.addAnnotation(annotationMethodObject);
    }

    protected MethodDeclaration buildMethod(String methodModifier, String tipoParametroDevuelto, String methodName, List<String> anotations,
                                            Exception e) {

        TypeParameter p = TypeAndVisibilityUtils.getTypeParameterBy(tipoParametroDevuelto);
        EnumSet<Modifier> modifiers = TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(methodModifier);

        MethodDeclaration method = new MethodDeclaration(modifiers, p, methodName);

        for (String anotation : anotations)
            method.addMarkerAnnotation(anotation);

        if (modifiers != null)
            method.setModifiers(modifiers);

        if (e != null)
            method.addThrownException(e.getClass());

        return method;
    }

    public void setBody(String body) {
        BlockStmt bs = new BlockStmt();
        bs.addStatement(body);
        setBody(bs);
    }

    public void setBody(BlockStmt contenidoMetodo) {
        methodDeclaration.setBody(contenidoMetodo);
    }



}
