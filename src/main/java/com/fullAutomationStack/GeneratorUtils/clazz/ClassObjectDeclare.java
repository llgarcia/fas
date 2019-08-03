package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.FileUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.body.BodyObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.HeaderObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ClassDeclaration;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.packages.PackageObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.support.FindBy;

public class ClassObjectDeclare {

    public void main() {

        CompilationUnit cu = new CompilationUnit();

        // DECLARO NOMBRE DE CLASE, TIPO, EXTENSIONES E INTERFACES
        ClassDeclaration classDeclaration = new ClassDeclaration("Locators");
        classDeclaration.addModifier("public");

        HeaderObjectClass headerObjectClass = new HeaderObjectClass();

        // DECLARACION DE PACKAGES

        PackageObject packageObject = new PackageObject("com.fullAutomationStack.GeneratorClass");

        // DECLARA IMPORTS
        ImportObjectList importList = new ImportObjectList(
                FindBy.class.getCanonicalName(),
                Getter.class.getCanonicalName(),
                Setter.class.getCanonicalName()
        );

        headerObjectClass.setClassDeclaration(classDeclaration);
        headerObjectClass.setPackageOfClass(packageObject);
        headerObjectClass.setImportsOfClass(importList);

        ClassOrInterfaceDeclaration clazz = headerObjectClass.compile(cu);

        // definir clase
        BodyObjectClass bodyObjectClass = new BodyObjectClass();


        // DECLARO VARIABLE
        VariableObject vo = new VariableObject("variable11", "String", "\"a\"", "public", "static", "final");

        // set annotation for Field
        AnnotationMethodObject o = new AnnotationMethodObject("FindBy", null, AnnotationTypes.NORMAL_ANNOTATION_EXPRESION);
        o.add("css", "\"[id='inputIdName]\"");

        // generate annotation with your values
        o = o.generateAll();

        // seteo anotacion en el FIELD
        vo.addAnnotations(o.getAnnotationExpresion());

        VariableObjectList vol = new VariableObjectList();
        vol.add(vo);
        bodyObjectClass.setVariableObjectList(vol);

        // DECLARO METODO

        MethodObject methodObject = new MethodObject();

        methodObject.setName("methodName");
        methodObject.setModifiers("public", "static");
        methodObject.setReturnType("void");
        methodObject.setMethodThrows(Exception.class);
        methodObject.addAnnotation(new AnnotationMethodObject("Test", Modifier.PUBLIC, AnnotationTypes.MARKER_ANNOTATION_EXPRESION).generateAll());
        // en el setBody se podrian meter variables construidas como se ha echo anteriormente...
        methodObject.setBody("String a = \"eeea\";");

        MethodObjectList mol = new MethodObjectList();
        mol.add(methodObject);
        bodyObjectClass.setMethodObjectList(mol);

        bodyObjectClass.compile(clazz);


//        System.out.println(cu.toString());


        // DECLARAR PATH

        FileUtils.setPath("/Users/lleir/IdeaProjects/fas/src/main/java/com/fullAutomationStack/GeneratorClass");
        FileUtils.setName("Locators");
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());


    }

}
