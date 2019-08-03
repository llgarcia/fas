package com.fullAutomationStack.GeneratorUtils;

//import com.fullAutomationStack.parser.definidor_de_contexto.ContextoObject;

import com.fullAutomationStack.FileUtils;
import com.fullAutomationStack.GeneratorUtils.clazz.PreDefinedClases.PD_Component;
import com.fullAutomationStack.GeneratorUtils.clazz.body.BodyObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.body.annotation.AnnotationMethodObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObject;
import com.fullAutomationStack.GeneratorUtils.clazz.body.variable.VariableObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.classComponents.Variable;
import com.fullAutomationStack.GeneratorUtils.clazz.header.HeaderObjectClass;
import com.fullAutomationStack.GeneratorUtils.clazz.header.classDeclaration.ClassDeclaration;
import com.fullAutomationStack.GeneratorUtils.clazz.header.imports.ImportObjectList;
import com.fullAutomationStack.GeneratorUtils.clazz.header.packages.PackageObject;
import com.fullAutomationStack.GeneratorUtils.clazz.utils.annotationsTypes.AnnotationTypes;
import com.fullAutomationStack.GenericCommon;
import com.fullAutomationStack.Propiedades;
import com.fullAutomationStack.WebDriver.WebElementAttribute;
import com.fullAutomationStack.WebDriver.WebElementObject;
import com.fullAutomationStack.parser.utils_parser.ParametroJavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by lleir on 24/3/18.
 */
public class GenerarClass {

    protected String class_packageDeclarado;
    protected List<String> class_importsDeclarados;
    protected String class_name;
    protected String extends_class_name;

    protected String tc_nombre_metodo;
    protected Exception tc_exception;
    protected String tc_tipoModifier;
    protected String tc_anotacion;
    protected List<String> tc_anotaciones;
    protected List<AnnotationExpr> tc_anotacionesCompletas;
    protected List<Parameter> tc_parametroMetodo;
    protected List<Parameter> tc_parametrosMetodo;
    protected List<Parameter> tc_parametrosInicializados;
    protected String tc_tipoReturn;
    protected MethodDeclaration tc_metodoDeclarado;
    protected List<MethodDeclaration> tc_metodosDeclarados;
    protected ConstructorDeclaration tc_constructorDeclarado;
    protected List<ConstructorDeclaration> tc_constructoresDeclarados;
    protected MethodCallExpr tc_llamadaMetodos;
    protected String tc_llamadaMetodoSimple;
    protected NodeList<Expression> tc_metodoArgumentos = new NodeList<Expression>();
    private List<VariableDeclarationExpr> tc_variables;

    protected BlockStmt cuerpoMetodoDeclarado;

    public GenerarClass() {

        this.tc_parametrosMetodo = new ArrayList<Parameter>();
        this.class_importsDeclarados = new ArrayList<String>();
        this.tc_metodosDeclarados = new ArrayList<MethodDeclaration>();
        this.tc_anotaciones = new ArrayList<String>();
        this.cuerpoMetodoDeclarado = new BlockStmt();
        this.tc_llamadaMetodos = new MethodCallExpr();
        this.tc_metodoArgumentos = new NodeList<Expression>();
        this.tc_constructoresDeclarados = new ArrayList<ConstructorDeclaration>();
        this.tc_parametrosInicializados = new ArrayList<Parameter>();
        this.tc_variables = new ArrayList<VariableDeclarationExpr>();
    }

    public void declararPackage() {
        //this.class_packageDeclarado = Propiedades.PACKAGE_DECLARATION;
    }

    public void declararPackage(String packagee) {
        this.class_packageDeclarado = packagee;
    }

    public void declararImports(String... imports) {
        for (String importt : imports)
            class_importsDeclarados.add(importt);
    }

    public void declararNombreDeClase(String nombreClase) {
        this.class_name = nombreClase;
    }

    public void declararExtendsDeClase(String extendsClase) {
        this.extends_class_name = extendsClase;
    }

    public void declararLlamadaMetodo(String nombreMetodo, String modifierMetodo, String tipoReturnMetodo, String anotacionMetodo, Exception exception) {
        declararNombreMetodo(nombreMetodo);
        declararModifierMetodo(modifierMetodo);
        declararReturnMetodo(tipoReturnMetodo);
        declararAnotacion(anotacionMetodo);
        declararException(exception);
    }

    public void declararNombreMetodo(String testCaseName) {
        this.tc_nombre_metodo = testCaseName;
    }

    public void declararModifierMetodo(String tipoModifier) {
        this.tc_tipoModifier = tipoModifier;
    }

    public void declararReturnMetodo(String tipoReturn) {
        this.tc_tipoReturn = tipoReturn;
    }

    public void declararAnotacion(String anotacion) {
        this.tc_anotaciones.add(anotacion);
    }

    public void declararException(Exception exception) {
        this.tc_exception = exception;
    }


    // TODO (?)
    private Class<?> definirTipo(String tipoParametro) {

        Class<?> tipo;

        switch (StringUtils.lowerCase(tipoParametro)) {
            case "string":
                tipo = new String().getClass();
                break;
            case "integer":
                tipo = new Integer(0).getClass();
                break;

            default:
                tipo = null;
        }

        return tipo;

    }

    public void definirLlamadaDeMetodoSimple(String nombreMetodo) {
        this.tc_llamadaMetodoSimple = nombreMetodo;
    }

    public void definirLlamadaDeMetodo(String nombreMetodo) {
        this.tc_llamadaMetodos.setName(nombreMetodo);
    }

    public void definirArgumentoDeMetodo(String argumento) {
        this.tc_metodoArgumentos.add(new NameExpr(argumento));
    }

    public void definirMetodoSimple(String llamada) {
        this.cuerpoMetodoDeclarado.addStatement(new NameExpr(llamada));
    }

    public void escribirMetodo() {
        this.tc_llamadaMetodos.setArguments(generarListExpresionsForMetodo());
        this.cuerpoMetodoDeclarado.addStatement(tc_llamadaMetodos);

        resetMetodoLlamada();
        resetMetodoArgumento();
    }

    public void escribirMetodoSimple() {
        if (!this.tc_llamadaMetodoSimple.isEmpty())
            this.cuerpoMetodoDeclarado.addStatement(this.tc_llamadaMetodoSimple);

        resetMetodoLlamada();
    }

    public void definirFor(String arg1, String var, String iterator, String then) {
        // definir la posiibildad de poder hacer un form dentro de un metodo declarado
    }

    public void generarMetodo() {
        MethodDeclaration metodo = getMethodDeclaration(this.tc_tipoModifier, this.tc_tipoReturn, this.tc_nombre_metodo, this.tc_anotaciones, this.tc_exception);
        metodo.setParameters(definirParametrosMetodoDeclarado());
        metodo.setBody(cuerpoMetodoDeclarado);
        this.tc_metodosDeclarados.add(metodo);

        resetMetodoAnotacion();
        resetMetodoArgumento();
        resetReturMetodo();
        resetParametros();
        resetCuerpoMetodo();

    }

    protected void resetMetodoLlamada() {
        this.tc_llamadaMetodos = new MethodCallExpr();
        this.tc_llamadaMetodoSimple = "";
    }

    protected void resetMetodosDeclarados() {
        this.tc_metodosDeclarados = new ArrayList<MethodDeclaration>();
    }

    protected void resetMetodoArgumento() {
        this.tc_metodoArgumentos = new NodeList<Expression>();
    }

    protected void resetMetodoAnotacion() {
        this.tc_anotaciones = new ArrayList<String>();
    }

    protected void resetParametros() {
        this.tc_parametrosMetodo = new ArrayList<Parameter>();
    }

    protected void resetParametrosInicializados() {
        this.tc_parametrosInicializados = new ArrayList<Parameter>();
    }

    protected void resetPackage() {
        this.class_packageDeclarado = "";
    }

    protected void resetImports() {
        this.class_importsDeclarados = new ArrayList<String>();
    }

    protected void resetConstructores() {
        this.tc_constructoresDeclarados = new ArrayList<ConstructorDeclaration>();
    }

    protected void resetCuerpoMetodo() {
        this.cuerpoMetodoDeclarado = new BlockStmt();
    }

    protected void resetReturMetodo() {
        this.tc_tipoReturn = "";
    }

    protected void resetVariablesInicio() {
        this.tc_variables = new ArrayList<VariableDeclarationExpr>();
    }

    protected void resetExtendsClase() {
        this.extends_class_name = "";
    }

    private NodeList<Expression> generarListExpresionsForMetodo() {

        NodeList<Expression> res = new NodeList<Expression>();

        for (Expression e : this.tc_metodoArgumentos)
            res.add(e);

        return res;
    }

    public NodeList<Parameter> definirParametrosMetodoDeclarado() {

        NodeList<Parameter> res = new NodeList<Parameter>();

        for (Parameter e : this.tc_parametrosMetodo)
            res.add(e);

        return res;
    }

    public void definirArgumentosMetodo(ParametroJavaParser... parametrosJavaParser) {

        Parameter parametro;

        for (ParametroJavaParser param : parametrosJavaParser) {
            parametro = new Parameter();
            parametro.setName(param.nombreArgumento);
            parametro.setType(definirTipo(param.tipoArgumento));
            this.tc_parametrosMetodo.add(parametro);
        }
    }

//	public void definirVariablesInicializadas(ParametroJavaParser...parametrosJavaParser) {
//
//		Parameter parametro;
//
//		for(ParametroJavaParser param : parametrosJavaParser){
//			parametro = new Parameter();
//			parametro.setName(param.nombreArgumento);
//			parametro.setType(param.tipoArgumento);
//			this.tc_parametrosInicializados.add(parametro);
//		}
//	}


    public void definirVariablesInicializadas(ParametroJavaParser... parametrosJavaParser) {
        VariableDeclarationExpr v;

        for (ParametroJavaParser param : parametrosJavaParser) {

            VariableDeclarator var = new VariableDeclarator();
            var.setType(param.tipoArgumento);
            var.setName(param.nombreArgumento);

            v = new VariableDeclarationExpr(var);

            if (StringUtils.isNotEmpty(param.anotacion))
                v.addAnnotation(param.anotacion);

            v.setModifiers(getModificadorMetodoSegunTipo(param.modifierArgumento));
            this.tc_variables.add(v);
        }
    }

    // hay que identificar que tipo de metodo se va a escribir. por ejemplo, si el metodo hace un 'return' y asigna a una variable hay que identificarlo y hacer la asignacion a variable.
    protected void generarLlamadaMetodoAsignacionAVariable_Objeto_Acceso_Action(String variableDondeAsigno, String contexto, String accesoContexto,
                                                                                String tipoDeVariable, String metodoParseadoAcceso, List<String> parametros) {

        MethodCallExpr method = generarMetodo_Objeto_Acceso_Action(contexto, accesoContexto, metodoParseadoAcceso, parametros);

        NodeList<VariableDeclarator> v = new NodeList<VariableDeclarator>();

        VariableDeclarator vd = null;

        if (StringUtils.isNotEmpty(tipoDeVariable)) {
            vd = new VariableDeclarator(PrimitiveType.charType(), variableDondeAsigno);

            if (StringUtils.equals(tipoDeVariable, "String"))
                vd.setType(new String().getClass());
            else if (StringUtils.equals(tipoDeVariable, "List<String>"))
                vd.setType("List<String>");

        } else {
            vd = new VariableDeclarator();
//            vd.setType(OApunteContable1.class);
            vd.setName(variableDondeAsigno);
        }

        vd.setInitializer(method);
        v.add(vd);

        FieldDeclaration aaa = new FieldDeclaration();

        aaa.setVariables(v);

        // hay que poner una clase de variable para que si no estan todos los metodos completos que no lo guarde....

        System.out.println(aaa.toString());
        cuerpoMetodoDeclarado.addStatement(aaa.toString());

    }

//    protected void instanciaContextObjectParaTestCase(ContextoObject cobject) {

//        NodeList<VariableDeclarator> v = new NodeList<VariableDeclarator>();
//        VariableDeclarator vd = new VariableDeclarator(PrimitiveType.charType(), cobject.controllerStr);
//        vd.setType(cobject.controllerStr);
//        vd.setName(cobject.contexto + " = new " + cobject.controllerStr + "()");
//        v.add(vd);

//        FieldDeclaration aaa = new FieldDeclaration();
//        aaa.setVariables(v);

//        cuerpoMetodoDeclarado.addStatement(aaa.toString());

//    }

//    protected void instanciaImportsPackageParaTestCase(ContextoObject co) {
//        if (!this.class_importsDeclarados.contains(co.controller.getCanonicalName()))
//            this.class_importsDeclarados.add(co.controller.getCanonicalName());
//    }

    protected void generarLlamada_Objeto_Acceso_Action(List<String> variableAAsignar, String nameExpresion, String fieldAccesExpresion,
                                                       String metodoParseado, List<String> parametros) {
        cuerpoMetodoDeclarado.addStatement(generarMetodo_Objeto_Acceso_Action(variableAAsignar, nameExpresion, fieldAccesExpresion, metodoParseado, parametros));
    }

    protected void generarLlamada_Objeto_Acceso_Action(String nameExpresion, String metodo, List<String> parametros) {
        cuerpoMetodoDeclarado.addStatement(generarMetodo_Objeto_Acceso_Action(nameExpresion, null, metodo, parametros));
    }


    // TODO hay que tratar a los patametros, ya que si es un booleano hay que identificarlo como tal. .. 23-10-2017
    private MethodCallExpr generarMetodo_Objeto_Acceso_Action(String nameExpresion, String fieldAccesExpresion,
                                                              String metodoParseado, List<String> parametros) {

        NameExpr clazz = new NameExpr(nameExpresion);
        FieldAccessExpr field = null;
        MethodCallExpr call = null;

        if (fieldAccesExpresion != null) {
            field = new FieldAccessExpr(clazz, fieldAccesExpresion);
            call = new MethodCallExpr(field, metodoParseado);
        } else {
            call = new MethodCallExpr(clazz, metodoParseado);
        }

        if (parametros != null)
            for (String parametro : parametros)
                call.addArgument(new NameExpr(parametro));

        System.out.println(call.toString());
        return call;
    }

    private MethodCallExpr generarMetodo_Objeto_Acceso_Action(List<String> variableAAsignar, String nameExpresion, String fieldAccesExpresion,
                                                              String metodoParseado, List<String> parametros) {

        NameExpr clazz = new NameExpr(nameExpresion);
        FieldAccessExpr field = null;
        MethodCallExpr call = null;

        if (fieldAccesExpresion != null) {
            field = new FieldAccessExpr(clazz, fieldAccesExpresion);
            call = new MethodCallExpr(field, metodoParseado);
        } else {
            call = new MethodCallExpr(clazz, metodoParseado);
        }

        if ((!StringUtils.containsIgnoreCase(metodoParseado, "copiar") || StringUtils.containsIgnoreCase(metodoParseado, "anotar"))
                && variableAAsignar != null && variableAAsignar.size() != 0)
            call.addArgument(new NameExpr(variableAAsignar.get(0)));

        else if (parametros != null)
            for (String parametro : parametros)
                call.addArgument(new NameExpr(parametro));

        System.out.println(call.toString());
        return call;
    }

    private AssignExpr asignarVariables(String queAsigno, String dondeAsigno) {
        NameExpr clazz = new NameExpr(queAsigno);
        NameExpr aSetear = new NameExpr(dondeAsigno);
        return new AssignExpr(clazz, aSetear, AssignExpr.Operator.ASSIGN);
    }

    public void definirAsignacionEnMetodo(String queAsigno, String dondeAsigno) {
        AssignExpr asignacion = asignarVariables(queAsigno, dondeAsigno);
        cuerpoMetodoDeclarado.addStatement(asignacion);
    }

    public void definirReturnEnMetodo(String queDevuelvo) {
        ReturnStmt a = new ReturnStmt(queDevuelvo);
        cuerpoMetodoDeclarado.addStatement(a);
    }

    private EnumSet<Modifier> getModificadorMetodoSegunTipo(String tipo) {
        EnumSet<Modifier> type;

        tipo = (tipo == null || StringUtils.equals(tipo, "") ? "" : tipo);

        switch (tipo) {
            case "public":
                type = EnumSet.of(Modifier.PUBLIC);
                break;
            case "private":
                type = EnumSet.of(Modifier.PRIVATE);
                break;
            default:
                type = EnumSet.of(Modifier.PUBLIC);
                break;
        }

        return type;
    }

    protected TypeParameter getTypeParameterBy(String type) {
        TypeParameter p = new TypeParameter("void");

        if (StringUtils.equalsIgnoreCase(type, "string"))
            p = new TypeParameter("String");

        return p;
    }

    // -------------------

    public void constructorProcess(String nombreConstructor, String nombreClassSteps) {
        ConstructorDeclaration cons = new ConstructorDeclaration(nombreConstructor);
        Parameter param = new Parameter();
        param.setType("Pagina" + nombreClassSteps);
        param.setName("pagina");
        cons.addParameter(param);
        cuerpoMetodoDeclarado.addStatement("steps = new " + nombreClassSteps + "Steps(pagina);");
        cons.setBody(cuerpoMetodoDeclarado);
        this.tc_constructoresDeclarados.add(cons);
    }

    public void constructorPagina(String nombreConstructor, String nombrePagina) {
        ConstructorDeclaration cons = new ConstructorDeclaration(nombreConstructor);
        Parameter param = new Parameter();
        param.setType("Pagina" + nombrePagina);
        param.setName("pagina");
        cons.addParameter(param);
        cuerpoMetodoDeclarado.addStatement("page = pagina;");
        cons.setBody(cuerpoMetodoDeclarado);
        this.tc_constructoresDeclarados.add(cons);
    }

    public void constructor(String nombrePagina) {
        ConstructorDeclaration cons = new ConstructorDeclaration(this.tc_nombre_metodo);
        cuerpoMetodoDeclarado.addStatement("pagina = new Pagina" + nombrePagina + "();");
        cuerpoMetodoDeclarado.addStatement("proceso = new " + nombrePagina + "Procesos(pagina);");
        cuerpoMetodoDeclarado.addStatement("steps = new " + nombrePagina + "Steps(pagina);");
        cuerpoMetodoDeclarado.addStatement("validar = new " + nombrePagina + "Validaciones(pagina);");
        cons.setBody(cuerpoMetodoDeclarado);
        this.tc_constructoresDeclarados.add(cons);
    }

    // TODO .. REVISAR! Y MIRAR SI SE PUEDE SEPARAR Y QUE NO SEA TAN ESPECIFICO, AHORA SOLAMENTE LO UTILIZO PARA UNA COSA...
    public void constructorSuper(String n1, String n2) {
        ConstructorDeclaration cons = new ConstructorDeclaration(this.tc_nombre_metodo);
        cuerpoMetodoDeclarado.addStatement("super(" + n1 + ", " + n2 + ");");
        cons.setBody(cuerpoMetodoDeclarado);
        this.tc_constructoresDeclarados.add(cons);
    }

    public void constructorSimple(String nombreConstructor, String consInt) {
        ConstructorDeclaration cons = new ConstructorDeclaration(nombreConstructor);

        if (consInt != null)
            cuerpoMetodoDeclarado.addStatement(consInt);

        cons.setBody(cuerpoMetodoDeclarado);
        this.tc_constructoresDeclarados.add(cons);

        resetCuerpoMetodo();
    }

    /**
     * *****************
     */

    protected MethodDeclaration getMethodDeclaration(String methodModifier, String tipoParametroDevuelto, String methodName, List<String> anotations,
                                                     Exception e) {

        TypeParameter p = getTypeParameterBy(tipoParametroDevuelto);
        EnumSet<Modifier> modifiers = getModificadorMetodoSegunTipo(methodModifier);

        MethodDeclaration method = new MethodDeclaration(modifiers, p, methodName);

        for (String anotation : anotations)
            method.addMarkerAnnotation(anotation);

        if (modifiers != null)
            method.setModifiers(modifiers);

        if (e != null)
            method.addThrownException(e.getClass());

        return method;
    }

    protected MethodDeclaration getMethodDeclaration(String methodModifier, String tipoParametroDevuelto, String methodName, String anotation,
                                                     Exception e) {

        declararAnotacion(anotation);
        return getMethodDeclaration(methodModifier, tipoParametroDevuelto, methodName, this.tc_anotaciones, e);
    }

    public void generarClase() {
        CompilationUnit cu = new CompilationUnit();
        ClassOrInterfaceDeclaration clase = cu.addClass(class_name);

        if (StringUtils.isNotEmpty(extends_class_name))
            clase.addExtendedType(extends_class_name);

        for (String importt : class_importsDeclarados)
            cu.addImport(new ImportDeclaration(new Name(importt), false, false));


        cu.setPackageDeclaration(new PackageDeclaration(new Name(class_packageDeclarado)));

        for (MethodDeclaration metodo : this.tc_metodosDeclarados)
            clase.addMember(metodo);

        // aqui he de diferenciar entreo aplicativos y generar esta clase en un sitio u otro.
        FileUtils.setPath(Propiedades.PATH);
        FileUtils.setName(class_name);
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());

    }

    public void generarClase(String app) {
        CompilationUnit cu = new CompilationUnit();
        ClassOrInterfaceDeclaration clase = cu.addClass(class_name);

        if (StringUtils.isNotEmpty(extends_class_name))
            clase.addExtendedType(extends_class_name);

        for (String importt : class_importsDeclarados)
            cu.addImport(new ImportDeclaration(new Name(importt), false, false));


        cu.setPackageDeclaration(new PackageDeclaration(new Name(class_packageDeclarado)));

        for (MethodDeclaration metodo : this.tc_metodosDeclarados)
            clase.addMember(metodo);

        // aqui he de diferenciar entreo aplicativos y generar esta clase en un sitio u otro.

        app = GenericCommon.reemplazarEspaciosYCapitalizarCadena(GenericCommon.replaceAccents(app));

        FileUtils.setPath(Propiedades.PATH + "\\" + app);
        FileUtils.crearDirectorioSiNoExiste(Propiedades.PATH, StringUtils.lowerCase(app));
        FileUtils.setName(class_name);
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());

    }

    public void generarClasePruebaParaFactorias(String path) {
        CompilationUnit cu = new CompilationUnit();

        cu.setPackageDeclaration(new PackageDeclaration(new Name(class_packageDeclarado)));

        ClassOrInterfaceDeclaration clase = cu.addClass(class_name);

        if (StringUtils.isNotEmpty(extends_class_name))
            clase.addExtendedType(extends_class_name);

        for (String importt : class_importsDeclarados)
            cu.addImport(new ImportDeclaration(new Name(importt), false, false));

        for (VariableDeclarationExpr param : this.tc_variables) {

            FieldDeclaration f = new FieldDeclaration();
            if (param.getAnnotations().size() != 0)
                f.addAnnotation(param.getAnnotations().get(0));

            // definimos el tipo
            String tipe = param.getModifiers().toString();
            if (StringUtils.containsIgnoreCase(tipe, "private")) {
                f.setPrivate(true);
            } else if (StringUtils.containsIgnoreCase(tipe, "protected")) {
                f.setProtected(true);
            } else if (StringUtils.containsIgnoreCase(tipe, "public")) {
                f.setPublic(true);
            }

            f.addVariable(param.getVariable(0));

            clase.addMember(f);
        }

        for (ConstructorDeclaration constructor : this.tc_constructoresDeclarados)
            clase.addMember(constructor);

        for (MethodDeclaration metodo : this.tc_metodosDeclarados)
            clase.addMember(metodo);

        FileUtils.setPath(path);
        FileUtils.setName(class_name);
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());

        resetPackage();
        resetImports();
        resetExtendsClase();
        resetVariablesInicio();
        resetConstructores();
        resetCuerpoMetodo();
        resetVariablesInicio();
        resetMetodoLlamada();
        resetMetodosDeclarados();

    }


    public void generateClassWebElements(List<WebElementObject> wo_list) {

        CompilationUnit cu = new CompilationUnit();
        ClassOrInterfaceDeclaration clase = cu.addClass("GeneratorClassWebElements");

        cu.setPackageDeclaration(new PackageDeclaration(new Name("com.fullAutomationStack.GeneratorClass")));
        cu.addImport(new ImportDeclaration(new Name("org.openqa.selenium.WebElement"), false, false));
        cu.addImport(new ImportDeclaration(new Name("org.openqa.selenium.support.FindBy"), false, false));

        for(WebElementObject wo : wo_list){

            NormalAnnotationExpr annotationExpresion = new NormalAnnotationExpr();
            annotationExpresion.setName(new Name("FindBy"));

            String locationCss = "";

            if(StringUtils.isNotEmpty(wo.getLocationParent()))
                locationCss = "\""+wo.getLocationParent() + " > " + wo.getLocation()+"\"";
            else
                locationCss = "\""+ wo.getLocation()+"\"";

            annotationExpresion.addPair("css", locationCss);

            VariableDeclarator vd = new VariableDeclarator();

            String variableName = wo.getTagName();

            boolean hasVariableName = false;

            if(wo.getAttributes() != null && wo.getAttributes().size() > 0) {

                try {

                    if (wo.hasValueByNameAttr("value")) {
                        variableName += "_value_" + wo.getValueByAttrName("value");
                        hasVariableName = true;
                    }else if (wo.hasValueByNameAttr("for")) {
                        variableName += "_for_" + wo.getValueByAttrName("for");
                        hasVariableName = true;
                    } else if (wo.hasValueByNameAttr("name")) {
                        variableName += "_name_" + wo.getValueByAttrName("name");
                        hasVariableName = true;
                    } else{

                        for (WebElementAttribute a : wo.getAttributes()) {

                            if(!StringUtils.contains(a.getNameAttribute(), "value")
                                    && !StringUtils.contains(a.getNameAttribute(), "for")
                                    && !StringUtils.contains(a.getNameAttribute(), "name")) {

                                if (StringUtils.contains(a.getNameAttribute(), "id")
                                        || StringUtils.contains(a.getNameAttribute(), "class")) {
                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
                                    hasVariableName = true;

                                } else if (!StringUtils.contains(a.getNameAttribute(), "href")
                                        && !StringUtils.contains(a.getNameAttribute(), "style")
                                        && !StringUtils.contains(a.getNameAttribute(), "src")
                                        ) {
                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
                                    hasVariableName = true;
                                } else
                                    variableName += "_" + locationCss;

                            }
                        }
                    }
                } catch (NullPointerException e){

                }
            }

            if (!hasVariableName)
                variableName += "_" + locationCss;

            variableName = StringUtils.replace(variableName, " ", "");
            variableName = StringUtils.replace(variableName, ">", "");
            variableName = StringUtils.replace(variableName, "/", "");
            variableName = StringUtils.replace(variableName, "-", "");
            variableName = StringUtils.replace(variableName, "[", "");
            variableName = StringUtils.replace(variableName, "]", "");
            variableName = StringUtils.replace(variableName, "(", "_");
            variableName = StringUtils.replace(variableName, ")", "_");
            variableName = StringUtils.replace(variableName, ".", "");
            variableName = StringUtils.replace(variableName, ",", "");
            variableName = StringUtils.replace(variableName, "|", "");
            variableName = StringUtils.replace(variableName, "\n", "");
            variableName = StringUtils.replace(variableName, ":", "");
            variableName = StringUtils.replace(variableName, "#", "");
            variableName = StringUtils.replace(variableName, "!", "");
            variableName = StringUtils.replace(variableName, "¡", "");
            variableName = StringUtils.replace(variableName, "?", "");
            variableName = StringUtils.replace(variableName, "¿", "");
            variableName = StringUtils.replace(variableName, "´", "");
            variableName = StringUtils.replace(variableName, "`", "");
            variableName = StringUtils.replace(variableName, ";", "");
            variableName = StringUtils.replace(variableName, "{", "");
            variableName = StringUtils.replace(variableName, "}", "");
            variableName = StringUtils.replace(variableName, "[", "");
            variableName = StringUtils.replace(variableName, "]", "");
            variableName = StringUtils.replace(variableName, "=", "_");
            variableName = StringUtils.replace(variableName, "'", "_");
            variableName = StringUtils.replace(variableName, "&", "_");
            variableName = StringUtils.replace(variableName, "%", "_");
            variableName = StringUtils.replace(variableName, "/", "_");
            variableName = StringUtils.replace(variableName, "\\", "_");
            variableName = StringUtils.replace(variableName, "\"", "_");

            vd.setName(variableName);
            vd.setType("WebElement");


            FieldDeclaration f = new FieldDeclaration();
            f.addAnnotation(annotationExpresion);
            f.addVariable(vd);
            f.setModifiers(getModificadorMetodoSegunTipo("public"));
            clase.addMember(f);

        }

        FileUtils.setPath("/Users/lleir/IdeaProjects/fas/src/main/java/com/fullAutomationStack/GeneratorClass");
        FileUtils.setName("GeneratorClassWebElements");
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());
    }

//  TODO new method...
    public void generateClassWebElementss(List<WebElementObject> wo_list) {

        CompilationUnit cu = new CompilationUnit();
        ClassDeclaration classDeclaration = new ClassDeclaration("GeneratorClassWebElementsNew");
        classDeclaration.addModifier("public");

        HeaderObjectClass cabecera = new HeaderObjectClass();
        BodyObjectClass cuerpo = new BodyObjectClass();

        PackageObject packageObject = new PackageObject("com.fullAutomationStack.GeneratorClass");

        ImportObjectList importList = new ImportObjectList(
                WebElement.class.getCanonicalName(),
                FindBy.class.getCanonicalName()
        );

        cabecera.setClassDeclaration(classDeclaration);
        cabecera.setPackageOfClass(packageObject);
        cabecera.setImportsOfClass(importList);

        ClassOrInterfaceDeclaration clazz = cabecera.compile(cu);


        VariableObjectList vol = new VariableObjectList();


        for(WebElementObject wo : wo_list) {

            String locationCss = null;

            if(StringUtils.isNotEmpty(wo.getLocationParent()))
                locationCss = "\""+wo.getLocationParent() + " > " + wo.getLocation()+"\"";
            else
                locationCss = "\""+ wo.getLocation()+"\"";

            AnnotationMethodObject o = new AnnotationMethodObject(FindBy.class.getSimpleName(), null, AnnotationTypes.NORMAL_ANNOTATION_EXPRESION);
            o.add("css", locationCss);
            o = o.generateAll();

            String variableName = wo.getTagName();
            boolean hasVariableName = false;

            if(wo.getAttributes() != null && wo.getAttributes().size() > 0) {
                try {

                    if (wo.hasValueByNameAttr("value")) {
                        variableName += "_value_" + wo.getValueByAttrName("value");
                        hasVariableName = true;
                    }else if (wo.hasValueByNameAttr("for")) {
                        variableName += "_for_" + wo.getValueByAttrName("for");
                        hasVariableName = true;
                    } else if (wo.hasValueByNameAttr("name")) {
                        variableName += "_name_" + wo.getValueByAttrName("name");
                        hasVariableName = true;
                    } else{

                        for (WebElementAttribute a : wo.getAttributes()) {

                            if(!StringUtils.contains(a.getNameAttribute(), "value")
                                    && !StringUtils.contains(a.getNameAttribute(), "for")
                                    && !StringUtils.contains(a.getNameAttribute(), "name")) {

                                if (StringUtils.contains(a.getNameAttribute(), "id")
                                        || StringUtils.contains(a.getNameAttribute(), "class")) {
                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
                                    hasVariableName = true;

                                } else if (!StringUtils.contains(a.getNameAttribute(), "href")
                                        && !StringUtils.contains(a.getNameAttribute(), "style")
                                        && !StringUtils.contains(a.getNameAttribute(), "src")
                                ) {
                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
                                    hasVariableName = true;
                                } else
                                    variableName += "_" + locationCss;

                            }
                        }
                    }
                } catch (NullPointerException e){

                }
            }

            if (!hasVariableName)
                variableName += "_" + locationCss;

            variableName = replace(variableName);

            VariableObject vo = new VariableObject(variableName, WebElement.class.getSimpleName(), null, "public");
            vo.addAnnotations(o.getAnnotationExpresion());
            vol.add(vo);
        }

        cuerpo.setVariableObjectList(vol);
        cuerpo.compile(clazz);

        FileUtils.setPath("/Users/lleir/IdeaProjects/fas/src/main/java/com/fullAutomationStack/GeneratorClass");
        FileUtils.setName("GeneratorClassWebElementsNew");
        FileUtils.setExtension("java");
        FileUtils.crearArchivo(cu.toString());
    }

    public void generateClassWebElementsSuperRefactor(List<WebElementObject> wo_list, String nameClass) {

        PD_Component c = new PD_Component();

        c.PUBLIC()
                .writePackage("com.fullAutomationStack.GeneratorClass")
                .writeNameClass(nameClass)
                .writeImport(WebElement.class.getCanonicalName())
                .writeImport(FindBy.class.getCanonicalName());

        for(WebElementObject wo : wo_list) {

            String locationCss = null;

            if(StringUtils.isNotEmpty(wo.getLocationParent()))
                locationCss = "\""+wo.getLocationParent() + " > " + wo.getLocation()+"\"";
            else
                locationCss = "\""+ wo.getLocation()+"\"";

            String variableName = wo.getTagName();
            boolean hasVariableName = false;

//            if(wo.getAttributes() != null && wo.getAttributes().size() > 0) {
//                try {
//
//                    if (wo.hasValueByNameAttr("value")) {
//                        variableName += "_value_" + wo.getValueByAttrName("value");
//                        hasVariableName = true;
//                    }else if (wo.hasValueByNameAttr("for")) {
//                        variableName += "_for_" + wo.getValueByAttrName("for");
//                        hasVariableName = true;
//                    } else if (wo.hasValueByNameAttr("name")) {
//                        variableName += "_name_" + wo.getValueByAttrName("name");
//                        hasVariableName = true;
//                    } else{
//
//                        for (WebElementAttribute a : wo.getAttributes()) {
//
//                            if(!StringUtils.contains(a.getNameAttribute(), "value")
//                                    && !StringUtils.contains(a.getNameAttribute(), "for")
//                                    && !StringUtils.contains(a.getNameAttribute(), "name")) {
//
//                                if (StringUtils.contains(a.getNameAttribute(), "id")
//                                        || StringUtils.contains(a.getNameAttribute(), "class")) {
//                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
//                                    hasVariableName = true;
//
//                                } else if (!StringUtils.contains(a.getNameAttribute(), "href")
//                                        && !StringUtils.contains(a.getNameAttribute(), "style")
//                                        && !StringUtils.contains(a.getNameAttribute(), "src")
//                                ) {
//                                    // TODO le meto todo el path (?)
//                                    variableName += "_" + wo.getPath();
////                                    variableName += "_" + a.getNameAttribute() + "_" + a.getValueAttribute() + "_" + locationCss;
//                                    hasVariableName = true;
//                                } else
//                                    variableName += "_" + wo.getPath();
//
//                            }
//                        }
//                    }
//                } catch (NullPointerException e){
//
//                }
//            }

            variableName = wo.getLocationParent() + wo.getLocation();

//            if (!hasVariableName)
//                variableName += "_" + locationCss;

            variableName = replace(variableName);

            c.writeVariablee(
                    new Variable()
                            .writeAnnotation(FindBy.class.getSimpleName(), "css", locationCss)
                            .PUBLIC()
                            .returnSpecificType("WebElement")
                            .writeVariable(variableName));


        }

        c.compilePrueba();
        c.save("/com/fullAutomationStack/GeneratorClass");
    }

    public String replace (String re){
        String variableName = "";
        variableName = StringUtils.replace(re, " ", "");
        variableName = StringUtils.replace(variableName, ">", "");
        variableName = StringUtils.replace(variableName, "+", "");
        variableName = StringUtils.replace(variableName, "-", "");
        variableName = StringUtils.replace(variableName, "/", "");
        variableName = StringUtils.replace(variableName, "-", "");
        variableName = StringUtils.replace(variableName, "[", "");
        variableName = StringUtils.replace(variableName, "]", "");
        variableName = StringUtils.replace(variableName, "(", "_");
        variableName = StringUtils.replace(variableName, ")", "_");
        variableName = StringUtils.replace(variableName, ".", "");
        variableName = StringUtils.replace(variableName, ",", "");
        variableName = StringUtils.replace(variableName, "|", "");
        variableName = StringUtils.replace(variableName, "\n", "");
        variableName = StringUtils.replace(variableName, ":", "");
        variableName = StringUtils.replace(variableName, "#", "");
        variableName = StringUtils.replace(variableName, "!", "");
        variableName = StringUtils.replace(variableName, "¡", "");
        variableName = StringUtils.replace(variableName, "?", "");
        variableName = StringUtils.replace(variableName, ">", "");
        variableName = StringUtils.replace(variableName, "<", "");
        variableName = StringUtils.replace(variableName, "¿", "");
        variableName = StringUtils.replace(variableName, "´", "");
        variableName = StringUtils.replace(variableName, "`", "");
        variableName = StringUtils.replace(variableName, ";", "");
        variableName = StringUtils.replace(variableName, "{", "");
        variableName = StringUtils.replace(variableName, "}", "");
        variableName = StringUtils.replace(variableName, "[", "");
        variableName = StringUtils.replace(variableName, "]", "");
        variableName = StringUtils.replace(variableName, "=", "_");
        variableName = StringUtils.replace(variableName, "'", "_");
        variableName = StringUtils.replace(variableName, "&", "_");
        variableName = StringUtils.replace(variableName, "%", "_");
        variableName = StringUtils.replace(variableName, "/", "_");
        variableName = StringUtils.replace(variableName, "\\", "_");
        variableName = StringUtils.replace(variableName, "\"", "_");

        return variableName;
    }
}
