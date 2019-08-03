package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.GeneratorUtils.clazz.body.BodyObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.header.HeaderObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObjectList;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/*
La finalidad de esta clase es construir la clase segun el 'header' y su 'body'.
 */

public class ClassObject {

    @Getter @Setter
    private ClassOrInterfaceDeclaration clazzDeclaration;

    @Getter @Setter
    private CompilationUnit cu;

    public ClassObject () {
        cu = new CompilationUnit();
        clazzDeclaration = new ClassOrInterfaceDeclaration();;
    }

    public void declareHeader(@NonNull HeaderObjectClass headerObjectClass) {
        headerObjectClass.compile(cu);
    }

    public void declareBody(BodyObjectClass bodyObjectClass) {

    }

}
