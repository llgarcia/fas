package com.fullAutomationStack.GeneratorUtils.clazz.header.packages;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.Name;
import lombok.Getter;
import lombok.Setter;

public class PackageObject {

    @Getter @Setter
    private PackageDeclaration opackage;

    public PackageObject (String pakage){
        this.opackage = new PackageDeclaration(new Name(pakage));
    }

    public String getPackageName() {
        return opackage.getName().asString();
    }
}
