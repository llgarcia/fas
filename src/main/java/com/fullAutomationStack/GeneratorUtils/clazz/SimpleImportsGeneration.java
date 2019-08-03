package com.fullAutomationStack.GeneratorUtils.clazz;

import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObject;
import lombok.Builder;

@Deprecated
@Builder(builderMethodName = "with", buildMethodName = "create")
public class SimpleImportsGeneration {

    public ImportObject[] imports;

}
