package com.fullAutomationStack.GeneratorUtils.clazz.header.imports;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ImportObjectList {

    @Getter @Setter
    private List<ImportObject> listImports;

    public ImportObjectList() {
        this.listImports = new ArrayList<ImportObject>();
    }

    public ImportObjectList(String...imports) {
        this.listImports = new ArrayList<ImportObject>();
        List<String> arr = (List<String>) ArrayUtils.convertList(imports);
        arr.forEach(strImport -> {
            this.listImports.add(new ImportObject(strImport, false));
        });
    }

    public void add(@NonNull ImportObject importObject){
        listImports.add(importObject);
    }

    public boolean existAndContent() {
        return listImports != null && listImports.size() > 0;
    }
}
