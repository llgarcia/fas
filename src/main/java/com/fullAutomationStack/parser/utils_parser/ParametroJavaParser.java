package com.fullAutomationStack.parser.utils_parser;

import com.github.javaparser.ast.expr.NormalAnnotationExpr;

public class ParametroJavaParser {

	public String modifierArgumento;
	public String nombreArgumento;
	public String tipoArgumento;
	public String anotacion;
	public NormalAnnotationExpr anotacionExpre;
	
	public ParametroJavaParser(String nombreArgumento, String tipoArgumento) {
		this.nombreArgumento = nombreArgumento;
		this.tipoArgumento = tipoArgumento;
	}
	
	public ParametroJavaParser(String nombreArgumento, String tipoArgumento, String anotacion) {
		this.nombreArgumento = nombreArgumento;
		this.tipoArgumento = tipoArgumento;
		this.anotacion = anotacion;
	}
	
	public ParametroJavaParser(String nombreArgumento, String tipoArgumento, String anotacion, String modifier) {
		this.nombreArgumento = nombreArgumento;
		this.tipoArgumento = tipoArgumento;
		this.anotacion = anotacion;
		this.modifierArgumento = modifier;
	}
	
}
