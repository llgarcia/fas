package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.FileUtils;
import com.fullAutomationStack.GeneralUtils.UtilityProperties;
import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.body.BodyObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.methods.MethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Method;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Variable;
import com.fullAutomationStack.GeneratorUtils.clazz.header.HeaderObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.header.annotations.AnnotationClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ClassDeclaration;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObject;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.InterfaceClassObject.InterfaceClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObject;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.packages.PackageObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Core_SimpleGenerationClass<Obj> extends GlobalEntitiesForJavaComponents<Obj> {

    private BodyObjectClass bodyObjectClass = new BodyObjectClass();
    private HeaderObjectClass headerObjectClass = new HeaderObjectClass();

    private PackageObject packageObject;
    private ImportObjectList importObjectList;
    private InterfaceClassObjectList interfaceClassObjectList;
    private ExtendedClassObjectList extendedClassObjectList;
    private ClassDeclaration classDeclaration;
    private AnnotationClassObjectList annotationClassObjectList;
    private MethodObjectList methodObjectList;
    protected VariableObjectList variableObjectList;
//    private AnnotationMethodObjectList annotationMethodObjectList;

    private ClassOrInterfaceDeclaration clazz;
    private CompilationUnit cu = new CompilationUnit();

    public Obj writePackage(String packagee) {
        this.packageObject = new PackageObject(packagee);
        return (Obj) this;
    }

    public Obj writeImports(String...imports) {
        createImportListIfNoExist();
        List<String> importsList = (List<String>) ArrayUtils.convertList(importObjectList);
        importsList.forEach(imp -> {
            this.importObjectList.add(new ImportObject(imp, false));
        });
        return (Obj) this;
    }

    public Obj writeImport(String importt, boolean staticImport, boolean asteriskImport) {
        createImportListIfNoExist();
        this.importObjectList.add(new ImportObject(importt, staticImport, asteriskImport));
        return (Obj) this;
    }

    public Obj writeImport(String importt) {
        createImportListIfNoExist();
        this.importObjectList.add(new ImportObject(importt, false, false));
        return (Obj) this;
    }

    private Obj createImportListIfNoExist(){
        this.importObjectList =
                this.importObjectList == null ? new ImportObjectList() : this.importObjectList;
        return (Obj) this;
    }

    public Obj writeNameClass(String nameClass) {
        createClassDeclarationIfNoExists();
        createModifiersIfNoExist();
        this.classDeclaration.setClassName(nameClass);
        this.classDeclaration.addModifier(modifiersObject.getModifiers().toArray(new String[modifiersObject.getModifiers().size()]));
        return (Obj) this;
    }

    public Obj writeExtendedClass(String extendedClassName) {
        createClassDeclarationIfNoExists();
        createExtendedClassObjectListIfNoExist();
        this.extendedClassObjectList.add(new ExtendedClassObject(extendedClassName));
        return (Obj) this;
    }

    public Obj writeExtendedClass(String extendedClassName, String importPakage) {
        writeExtendedClass(extendedClassName);
        writeImport(importPakage, false, false);
        return (Obj) this;
    }

    private Obj createExtendedClassObjectListIfNoExist() {
        this.extendedClassObjectList = this.extendedClassObjectList == null ? new ExtendedClassObjectList(): this.extendedClassObjectList;
        return (Obj) this;
    }

    private Obj createClassDeclarationIfNoExists(){
        this.classDeclaration =
                this.classDeclaration == null ? new ClassDeclaration() : this.classDeclaration;
        return (Obj) this;
    }

    public Obj compile() {
        this.headerObjectClass.setClassDeclaration(this.classDeclaration);
        this.headerObjectClass.setPackageOfClass(this.packageObject);
        this.headerObjectClass.setImportsOfClass(this.importObjectList);
        this.clazz = this.headerObjectClass.compile(cu);
        this.bodyObjectClass.setExtendedClassObjectList(this.extendedClassObjectList);
        this.bodyObjectClass.setMethodObjectList(this.methodObjectList);
        this.bodyObjectClass.setVariableObjectList(this.variableObjectList);
        this.bodyObjectClass.compile(clazz);
        return (Obj) this;
    }

    public Obj compilePrueba() {
        this.headerObjectClass.setClassDeclaration(this.classDeclaration);
        this.headerObjectClass.setPackageOfClass(this.packageObject);
        this.headerObjectClass.setImportsOfClass(this.importObjectList);
        this.clazz = this.headerObjectClass.compile(cu);
        this.bodyObjectClass.setExtendedClassObjectList(this.extendedClassObjectList);
        this.bodyObjectClass.setMethodObjectList(this.methodObjectList);
        this.bodyObjectClass.setVariableObjectList(this.variableObjectList);
        this.bodyObjectClass.compile(clazz);
        return (Obj) this;
    }

    public void save(String path) {
        save(path, this.classDeclaration.getClassName());
    }

    public void save(String path, String nameClass) {
        UtilityProperties.loadInit();
        String pathUserDir = UtilityProperties.getPropertie("main.dir");
        FileUtils.setPath(UtilityProperties.transformPath(pathUserDir) + UtilityProperties.transformPath(path));
        FileUtils.setName(nameClass);
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());
    }


    public Obj writeVariablee(Variable v){
        createVariableListIfNoExist();
        variableObjectList.add(v);
        return (Obj) this;
    }

    private void createVariableListIfNoExist() {
        if (this.variableObjectList == null)
            variableObjectList = new VariableObjectList();
    }

    public Obj writeMethod(Method m) {
        createMethodListIfNoExist();
        methodObjectList.add(m);
        return (Obj) this;
    }

    private void createMethodListIfNoExist() {
        if(this.methodObjectList == null)
            methodObjectList = new MethodObjectList();
    }







}
