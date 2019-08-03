package com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes;

import com.fullAutomationStack.GeneratorUtils.clazz.SimpleMethodGeneration;
import com.fullAutomationStack.GeneratorUtils.clazz.SimpleVariableGeneration;
import lombok.Builder;

import java.util.List;

@Builder
public class OrchestratorObject {

    public List<SimpleVariableGeneration> variable;
    public List<SimpleMethodGeneration> method;

}
