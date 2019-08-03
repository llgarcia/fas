package com.fullAutomationStack.GeneratorUtils.clazz.header;

import com.fullAutomationStack.GeneratorUtils.clazz.header.annotations.AnnotationClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ClassDeclaration;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObject;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.packages.PackageObject;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/*
La clase tiene contenido de antes de empezar con el cuerpo de dicha clase, como:
    - package
    - imports
    - anotaciones de clase
    - implements / extendeds
*/

public class HeaderObjectClass {

    @Getter
    private PackageObject packageObject;

    @Getter
    private ImportObjectList importObjectList;

    @Getter @Setter
    private AnnotationClassObjectList annotationClasObjectList;

    @Getter @Setter
    private ClassDeclaration classDeclaration;

    public HeaderObjectClass setPackageOfClass(@NonNull String packageOfClass) {
        this.packageObject = new PackageObject(packageOfClass);
        return this;
    }

    public HeaderObjectClass setPackageOfClass(@NonNull PackageObject packageObject) {
        this.packageObject = packageObject;
        return this;
    }

    public HeaderObjectClass setPackageOfClass(@NonNull String packageOfClass, ImportObjectList importObjectList) {
        this.packageObject = new PackageObject(packageOfClass);
        this.importObjectList = importObjectList;
        return this;
    }

    public HeaderObjectClass setImportsOfClass(ImportObjectList importObjectList){
        this.importObjectList = importObjectList;
        return this;
    }

    public ClassOrInterfaceDeclaration compile(CompilationUnit cu){
        compilePackages(cu);
        compileImports(cu);
        return compileClassName(cu);
    }

    private void compileClassDeclaration(CompilationUnit cu) {
        cu.addClass(this.classDeclaration.getClassName());
    }

    private void compilePackages(CompilationUnit cu){
        cu.setPackageDeclaration(packageObject.getOpackage());
    }

    private ClassOrInterfaceDeclaration compileClassName(CompilationUnit cu){
        return cu.addClass(classDeclaration.getClassName(), classDeclaration.getModifiers());
    }

    private void compileImports(CompilationUnit cu) {
        if (importObjectList != null && importObjectList.existAndContent())
            importObjectList.getListImports().forEach(importObject -> cu.addImport(importObject.getOimport()));
    }

    private void compileAnnotations(CompilationUnit cu) {
        // todo revisar como hacer
//        annotationClasObjectList.getAnnotationDeclarationObjectList().forEach(
//                annotationClassObject ->  cu.addAnnotationDeclaration(annotationClassObject.getAnnotationDeclaration())));
//        cu.addAnnotationDeclaration());
    }


}
