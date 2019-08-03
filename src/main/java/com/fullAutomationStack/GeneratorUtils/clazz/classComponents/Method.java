package com.fullAutomationStack.GeneratorUtils.clazz.classComponents;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.GlobalEntitiesForJavaComponents;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.TypeParameter;
import lombok.Getter;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public class Method extends GlobalEntitiesForJavaComponents<Method> {

    @Getter
    private MethodObject methodObject;

    public Method() {
        modifiersObject = new ModifiersObject();
        annotationMethodObjectList = new AnnotationMethodObjectList();
    }

    public Method addException() {

        return this;
    }

    public Method addMethod(String methodName) {
        int to = 0;
        String returnType = "";
        String[] types = null;

        to = modifiersObject.getModifiers().size() - 2;

        Map<String, List<String>> map
                = ArrayUtils.separateArrayInMap("modifiers", "returnType", 0, to, modifiersObject.getModifiers());

        returnType = map.get("returnType").get(0);
        types = map.get("modifiers").toArray(new String[map.get("modifiers").size()]);

        this.methodObject = new MethodObject(methodName, returnType, types);

        // todo SIMPLIFICAR
        if(annotationMethodObjectList != null && annotationMethodObjectList.getAnnotationDeclarationObjectList().size()!=0)
            if(annotationMethodObjectList.existingAnnotations())
                annotationMethodObjectList.getAnnotationDeclarationObjectList().forEach(
                        annotation -> {
                            methodObject.addAnnotation(annotation.getAnnotationExpresion());
                        }
                );

        createModifiersIfNoExist();
        return this;
    }

    public Method setParameter(String type, String param) {
        // TODO
        return this;
    }

    public Method writeBodyMethod(String bodyMethod) {
        this.methodObject.setBody(bodyMethod);
        return this;
    }
}
