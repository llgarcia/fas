package com.fullAutomationStack.GeneratorUtils.clazz.body;

/*
Se almacena el contenido de la clase como los metodos o variables de esta:
    - annotaciones
    - metodos
    - variables
*/

import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObjectList;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BodyObjectClass {

    @Getter @Setter
    private MethodObjectList methodObjectList;

    @Getter @Setter
    private VariableObjectList variableObjectList;

    @Getter @Setter
    private ExtendedClassObjectList extendedClassObjectList;

    public void compile(ClassOrInterfaceDeclaration clazz){
        compileVariables(clazz);
        compileMethods(clazz);
        compileExtendeds(clazz);
    }

    public void compileVariables(ClassOrInterfaceDeclaration clazz) {
       if(variableObjectList != null && variableObjectList.existingVariables1())
            variableObjectList.getListVariables1().forEach(var -> {
                clazz.addMember(var.getV().getFieldDeclaration());
            });
    }

    public void compileMethods(ClassOrInterfaceDeclaration clazz){
        if(methodObjectList != null && methodObjectList.existingMethods1())
            methodObjectList.getListMethods1().forEach(meth -> {
                clazz.addMember(meth.getMethodObject().getMethodDeclaration());
            });
    }

    public void compileExtendeds(ClassOrInterfaceDeclaration clazz){
        if(extendedClassObjectList != null && extendedClassObjectList.existingExtends())
            extendedClassObjectList.getExtendedClassObjectList().forEach(meth -> {
                clazz.addExtendedType(meth.getNameClass());
            });
    }



}
