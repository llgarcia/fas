package com.fullAutomationStack.WebDriver;

/**
 * Created by lleir on 2/6/18.
 */
public class WebElementAttribute {

    private String nameAttribute;
    private String valueAttribute;
    private String ownerElement;
    private String offsetParent;

    public WebElementAttribute(String nameAttribute, String valueAttribute) {
        this.nameAttribute = nameAttribute;
        this.valueAttribute = valueAttribute;
    }

    public WebElementAttribute(String nameAttribute, String valueAttribute, String ownerElement, String offsetParent) {
        this.nameAttribute = nameAttribute;
        this.valueAttribute = valueAttribute;
        this.ownerElement = ownerElement;
        this.offsetParent = offsetParent;
    }

    public String getNameAttribute() {
        return nameAttribute;
    }

    public void setNameAttribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
    }

    public String getValueAttribute() {
        return valueAttribute;
    }

    public void setValueAttribute(String valueAttribute) {
        this.valueAttribute = valueAttribute;
    }
}
