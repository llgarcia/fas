package com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObject;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ExtendedClassObject.ExtendedClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.InterfaceClassObject.InterfaceClassObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import com.github.javaparser.ast.Modifier;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassDeclaration {

    @Getter @Setter
    private String className;

    @Getter @Setter
    private Modifier modifier;

    @Getter
    private Modifier[] modifiers;

    @Getter @Setter
    private ExtendedClassObject extendedClass;


    @Getter @Setter
    private ExtendedClassObjectList extendedClassObjectList;

    @Getter @Setter
    private InterfaceClassObjectList interfaceClassObjectList;

    public ClassDeclaration (){
        this.extendedClassObjectList = new ExtendedClassObjectList();
    }

    public ClassDeclaration (@NonNull String className){
        this.className = className;
        this.extendedClass = null;
        this.extendedClassObjectList = new ExtendedClassObjectList();
    }

    public ClassDeclaration (@NonNull String className, String extendedClass){
        this.className = className;
        this.extendedClass = extendedClass == null || StringUtils.equals(extendedClass, "\"\"") ? null : new ExtendedClassObject(extendedClass);
        this.extendedClassObjectList = new ExtendedClassObjectList();
    }

    public ClassDeclaration(@NonNull String className, String...modifiers){
        this.className = className;
//        this.modifiers = (List<Modifier>)
//                ArrayUtils.convertList(
//                        TypeAndVisibilityUtils.getModificadorMetodoSegunTipo(modifiers));
        this.extendedClassObjectList = new ExtendedClassObjectList();
    }

    public void addModifiers(Modifier...modifiers){

    }

    public void setImplements(String...implementations){

    }

    public void addModifier(String...modifiers){
        List<String> temp = (List<String>) ArrayUtils.convertList(modifiers);
        List<Modifier> tempModificers = new ArrayList<Modifier>();

        temp.forEach(mod -> {
            tempModificers.add(TypeAndVisibilityUtils.getModifierMetodoSegunTipo(mod));
        });

        this.modifiers = (Modifier[]) tempModificers.toArray(new Modifier[tempModificers.size()]);
    }

    public void addExtended(@NonNull String...extendeds) {
        List<String> temp = (List<String>) ArrayUtils.convertList(extendeds);
        temp.forEach(ext -> {
            extendedClassObjectList.add(new ExtendedClassObject(ext));
        });
    }

}
