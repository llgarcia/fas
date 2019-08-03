package com.fullAutomationStack;

import com.fullAutomationStack.GeneralUtils.UtilityProperties;
import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.GenerarClass;
import com.fullAutomationStack.GeneratorUtils.clazz.*;
import com.fullAutomationStack.GeneratorUtils.clazz.PreDefinedClases.PD_Action;
import com.fullAutomationStack.GeneratorUtils.clazz.PreDefinedClases.PD_Component;
import com.fullAutomationStack.GeneratorUtils.clazz.PreDefinedClases.PD_Locators;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Method;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Variable;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ClassDeclaration;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.TypeAndVisibilityUtils;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lleir on 20/7/18.
 */
public class GenerateTest {

    @Test public void generador() throws Exception {
        GenerarClass a = new GenerarClass();
        a.generateClassWebElementss(null);
    }

    @Test public void prueba(){
        TypeAndVisibilityUtils.getModificadorMetodoSegunTipo("public", "static", "void");
    }

    @Test public void prueba2() {
        new ClassObjectDeclare().main();
    }

    @Test public void clasDeclareModif() {
        ClassDeclaration a = new ClassDeclaration("a");
        a.addModifier("public", "abstract");
    }

    @Test public void testSimpleGenerateTest() {
        Core_SimpleGenerationClass s = new Core_SimpleGenerationClass();

//        s.writePackage("com.fullAutomationStack.GeneratorClass");
//        s.writeImport(WebElement.class.getCanonicalName());
//        s.writeImport(FindBy.class.getCanonicalName());
//
//        s.PUBLIC().writeNameClass("LocatorsX1");
////        s.writeExtendedClass(ClassObject.class.getSimpleName(), ClassObject.class.getCanonicalName());
//
//        s.PUBLIC().STATIC().STRING().writeVariable("variablePrueba");
//        s.PUBLIC().STATIC().STRING().writeVariable("variablePrueba");
//        s.PUBLIC().STATIC().STRING().writeVariable("variablePrueba");
//        s.PUBLIC().STATIC().STRING().writeVariable("variablePrueba");
//
//        s.compile().save("/java/com/fullAutomationStack/GeneratorClass", "PruebaAutodefinida");
    }

    @Test public void testSimplePD_Locators() {

        PD_Locators c = new PD_Locators();
        c.writeNameClass("Locators")
                .writePackage("com.fullAutomationStack.GeneratorClass")
                .writeVariablee(
                    new Variable()
                        .PUBLIC()
                        .STATIC()
                        .FINAL()
                        .returnSpecificType("String")
                        .writeVariable("CSS_VARIABLE1").writeVariableContent("\"[id='id_1323']\"")
                ).writeVariablee(
                    new Variable()
                            .PUBLIC()
                            .STATIC()
                            .FINAL()
                            .returnSpecificType("String")
                            .writeVariable("CSS_VARIABLE2").writeVariableContent("\"[id='id_2222']\"")
                ).compilePrueba()
                .save("/com/fullAutomationStack/GeneratorClass");

        PD_Component p = new PD_Component();
        p.writePackage("com.fullAutomationStack.GeneratorClass")
                .writeNameClass("ComponentClass1")
                .writeImport(FindBy.class.getCanonicalName())
                .writeImport(WebElement.class.getCanonicalName())
                .writeImport("com.fullAutomationStack.GeneratorClass.Locators.*")
                .writeVariablee(
                        new Variable()
                                .writeAnnotation(FindBy.class.getSimpleName(),"css", "Locators.CSS_VARIABLE1")
                                .PUBLIC()
                                .returnSpecificType("WebElement")
                                .writeVariable("inputName")
                ).writeMethod(
                    new Method()
                            .PUBLIC()
                            .VOID()
                            .addMethod("clickInput")
                            .writeBodyMethod("inputName.click();")
                )
                .compilePrueba()
                .save("/com/fullAutomationStack/GeneratorClass");
    }



    @Test public void arrayTest(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        Map<String, List<String>> map = ArrayUtils.separateArrayInMap("a", "e", 0, 5, list);

        map.forEach((key, value) -> {
            System.out.println("K: " + key + "    VALUE: ");
            value.forEach(e ->System.out.println(e));
        });
    }

    @Test
    public void props() {
        UtilityProperties.loadInit();
    }
}
