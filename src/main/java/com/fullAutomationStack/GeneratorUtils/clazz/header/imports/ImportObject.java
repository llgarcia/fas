package com.fullAutomationStack.GeneratorUtils.clazz.header.imports;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.expr.Name;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportObject {

    @Getter @Setter
    private ImportDeclaration oimport;

    public ImportObject (String oimport, boolean isStatic, boolean isAsterisk){
        this.oimport = new ImportDeclaration(new Name(oimport), isStatic, isAsterisk);
    }

    public ImportObject (String oimport, boolean isStatic){
        this.oimport = new ImportDeclaration(new Name(oimport), isStatic, false);
    }

    public ImportObject(String oimport) {
        this.oimport = new ImportDeclaration(new Name(oimport), false, false);
    }


}
