package com.fullAutomationStack.GeneratorUtils.clazz.classComponents;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.GlobalEntitiesForJavaComponents;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObject;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Variable extends GlobalEntitiesForJavaComponents<Variable> {

    @Getter
    private VariableObject v;

    public Variable() {
        modifiersObject = new ModifiersObject();
        annotationMethodObjectList = new AnnotationMethodObjectList();
    }

    public Variable writeVariableContent(String content) {
        this.v.setInitialazer(content);
        return this;
    }

    public Variable writeVariable(String variableName) {
        int to = 0;
        String returnType = "";
        String[] stringArray = null;

        to = modifiersObject.getModifiers().size() - 2;

        Map<String, List<String>> map
                = ArrayUtils.separateArrayInMap("modifiers", "returnType", 0, to, modifiersObject.getModifiers());

        returnType = map.get("returnType").get(0);
        stringArray = map.get("modifiers").toArray(new String[map.get("modifiers").size()]);

        this.v = new VariableObject(variableName, returnType, null, stringArray);

        // todo SIMPLIFICAR
        if(annotationMethodObjectList != null && annotationMethodObjectList.getAnnotationDeclarationObjectList().size()!=0)
            if(annotationMethodObjectList.existingAnnotations())
                annotationMethodObjectList.getAnnotationDeclarationObjectList().forEach(
                        annotation -> {
                            v.addAnnotations(annotation.getAnnotationExpresion());
                        }
                );

        createModifiersIfNoExist();
        return this;
    }
}
