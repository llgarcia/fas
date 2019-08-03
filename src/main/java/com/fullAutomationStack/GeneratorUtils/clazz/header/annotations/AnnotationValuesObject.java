package com.fullAutomationStack.GeneratorUtils.clazz.header.annotations;

import lombok.Getter;
import lombok.Setter;

public class AnnotationValuesObject {

    @Getter @Setter
    private String key;

    @Getter @Setter
    private String value;

    @Getter @Setter
    private boolean isWithKeyAndValue;

    public AnnotationValuesObject(String key, String value, boolean isWithKeyAndValue ) {
        this.key = key;
        this.value = value;
        this.isWithKeyAndValue = isWithKeyAndValue;
    }

    public AnnotationValuesObject(String value, String key) {
        this.value = value;
        this.key = key;
        this.isWithKeyAndValue = true;
    }

    public AnnotationValuesObject(String value, boolean isWithKeyAndValue ) {
        this.value = value;
        this.isWithKeyAndValue = isWithKeyAndValue;
    }

}
