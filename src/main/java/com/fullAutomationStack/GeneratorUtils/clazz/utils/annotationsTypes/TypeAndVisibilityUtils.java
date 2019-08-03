package com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.type.TypeParameter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;
import java.util.List;

import static com.fullAutomationStack.GeneratorUtils.ArrayUtils.convertList;

public class TypeAndVisibilityUtils {

//    public static EnumSet<Modifier> getModificadorMetodoSegunTipo(@NonNull String tipo) {
//        EnumSet<Modifier> type;
//        switch (tipo) {
//            case "public":
//                type = EnumSet.of(Modifier.PUBLIC);
//                break;
//            case "private":
//                type = EnumSet.of(Modifier.PRIVATE);
//                break;
//            case "protected":
//                type = EnumSet.of(Modifier.PROTECTED);
//                break;
//            case "abstract":
//                type = EnumSet.of(Modifier.ABSTRACT);
//                break;
//            case "static":
//                type = EnumSet.of(Modifier.STATIC);
//                break;
//            case "final":
//                type = EnumSet.of(Modifier.FINAL);
//                break;
//            case "\"\"":
//                type = EnumSet.of(Modifier.PUBLIC);
//                break;
//
//                default:
//                    type = EnumSet.of(Modifier.PUBLIC);
//                    break;
//        }
//
//        return type;
//    }

    public static Modifier getModifierMetodoSegunTipo(@NonNull String tipo) {
        Modifier type;
        switch (tipo) {
            case "public":
                type = Modifier.PUBLIC;
                break;
            case "private":
                type = Modifier.PRIVATE;
                break;
            case "protected":
                type = Modifier.PROTECTED;
                break;
            case "abstract":
                type = Modifier.ABSTRACT;
                break;
            case "static":
                type = Modifier.STATIC;
                break;
            case "final":
                type = Modifier.FINAL;
                break;
            case "\"\"":
                type = Modifier.PUBLIC;
                break;
            default:
                type = Modifier.PUBLIC;
                break;
        }

        return type;
    }

    // todo: creo que devuelve el inicio de un metodo, por ejemplo: "public static" con lo cual se pueden concatenar
    public static EnumSet<Modifier> getModificadorMetodoSegunTipo(@NonNull String...types) {
        EnumSet<Modifier> type = null;

        List<String> allModifiers = (List<String>) ArrayUtils.convertList(types);

        int sizeExpectedModifiers = allModifiers.size();

        if(sizeExpectedModifiers == 1){
            type = EnumSet.of(
                    getModifierMetodoSegunTipo(allModifiers.get(0)));

        } else if (sizeExpectedModifiers == 2) {
            type = EnumSet.of(
                    getModifierMetodoSegunTipo(allModifiers.get(0)),
                    getModifierMetodoSegunTipo(allModifiers.get(1)));

        } else if (sizeExpectedModifiers == 3) {
            type = EnumSet.of(
                    getModifierMetodoSegunTipo(allModifiers.get(0)),
                    getModifierMetodoSegunTipo(allModifiers.get(1)),
                    getModifierMetodoSegunTipo(allModifiers.get(2)));

        } else if (sizeExpectedModifiers == 4) {
            type = EnumSet.of(
                    getModifierMetodoSegunTipo(allModifiers.get(0)),
                    getModifierMetodoSegunTipo(allModifiers.get(1)),
                    getModifierMetodoSegunTipo(allModifiers.get(2)),
                    getModifierMetodoSegunTipo(allModifiers.get(3)));

        } else if (sizeExpectedModifiers == 5) {
            type = EnumSet.of(
                    getModifierMetodoSegunTipo(allModifiers.get(0)),
                    getModifierMetodoSegunTipo(allModifiers.get(1)),
                    getModifierMetodoSegunTipo(allModifiers.get(2)),
                    getModifierMetodoSegunTipo(allModifiers.get(3)),
                    getModifierMetodoSegunTipo(allModifiers.get(4)));

        }

        return type;
    }

    public static TypeParameter getTypeParameterBy(String type) {
        return new TypeParameter(type);
    }




}
