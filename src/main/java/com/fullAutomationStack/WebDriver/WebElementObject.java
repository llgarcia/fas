package com.fullAutomationStack.WebDriver;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.Engines.Engine;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lleir on 31/5/18.
 */
public class WebElementObject {

    private WebElement webElement;
    private WebElement webElementFrame;
    private WebDriver driver;   // esto servira para localizar en el caso de que esté en un iframe (vemos como lo utilizamos)
    private String id;
    private String location;
    private String locationParent;
    @Getter
    private String path;
    private String cssValue;
    private String clas;
    private String tagname;
    private String text;
//    private int positionInSourceCode;
    private List<WebElementAttribute> attributes;

    public WebElementObject(WebDriver driver, WebElement webElementFrame, WebElement webElement, int positionInSourceCode, List<WebElementAttribute> attributes, String location, String locationParent) {
        this.webElement = webElement;
        this.driver = driver;
        this.tagname = webElement.getTagName();
//        this.positionInSourceCode = positionInSourceCode;
        this.attributes = attributes;
        this.location = location;
        this.locationParent = locationParent;
        this.text = webElement.getText();
        this.webElementFrame = webElementFrame;
//        this.webElement = this.driver.findElement(By.cssSelector(location));
    }

    public WebElementObject(WebDriver driver, WebElement e) {
        this.driver = driver;
        this.webElement = e;
        this.tagname = e.getTagName();
        this.text = e.getText();
        this.webElementFrame = e;
        this.attributes = getAttributesOfWebElement(e);
        this.location = e.getTagName() + ":nth-child(1)";
        this.locationParent = getParentsNodesOfWebElementByCss(e.getTagName() + ":nth-child(1)", e);
        String loc = e.getTagName();
        this.path = StringUtils.isNotEmpty(locationParent) ? locationParent + " > " + loc : loc;
    }

    public WebElementObject(WebDriver driver, WebElement e, int lastPositionOfWebElementByTagInMap, int positionWebElementCssSelector) {
        this.driver = driver;
        this.tagname = e.getTagName();
        this.text = e.getText();
        this.attributes = getAttributesOfWebElementWithPos(e, lastPositionOfWebElementByTagInMap);
        this.location = e.getTagName() + ":nth-child("+positionWebElementCssSelector+")";
        this.locationParent = getParentsNodesOfWebElementByCss(e.getTagName() + ":nth-child("+positionWebElementCssSelector+")", e);
        String loc = e.getTagName();
        this.path = StringUtils.isNotEmpty(locationParent) ? locationParent + " > " + loc : loc;
    }

    public WebElementObject(WebDriver frame, WebElement e, WebElement inFrame, int lastPositionOfWebElementByTagInMap, int nextPositionOfWebElementByTabInMap, Engine.WebObjectBeforeAtFrame webObjectBeforeFrame) {
        String frameCssSelector = getIdentificationFrame(webObjectBeforeFrame);
        this.driver = frame;
        this.tagname = inFrame.getTagName();
        this.text = inFrame.getText();
        this.attributes = getAttributesOfWebElementWithPos(inFrame, lastPositionOfWebElementByTagInMap);
        this.location = inFrame.getTagName() + ":nth-child("+nextPositionOfWebElementByTabInMap+")";
        this.locationParent = webObjectBeforeFrame.getFrameTagName() +
                getParentsNodesOfWebElementByCss(inFrame.getTagName() + frameCssSelector  + inFrame.getTagName() +":nth-child("+nextPositionOfWebElementByTabInMap+")", inFrame);
        String loc = inFrame.getTagName();
        this.path = StringUtils.isNotEmpty(locationParent) ? locationParent + " > " + loc : loc;
    }

    public WebElementObject(WebDriver driver, WebElement e, WebElement inFrame, Engine.WebObjectBeforeAtFrame webObjectBeforeFrame) {
        String frameCssSelector = getIdentificationFrame(webObjectBeforeFrame);
        this.driver = driver;
        this.tagname = inFrame.getTagName();
        this.text = inFrame.getText();
        this.attributes = getAttributesOfWebElement(inFrame);
        this.location = inFrame.getTagName() + ":nth-child(1)";
        this.locationParent = webObjectBeforeFrame.getFrameTagName() + frameCssSelector +
                getParentsNodesOfWebElementByCss(webObjectBeforeFrame.getFrameTagName() + frameCssSelector + inFrame.getTagName() + ":nth-child(1)", inFrame);
        String loc = inFrame.getTagName();
        this.path = StringUtils.isNotEmpty(locationParent) ? locationParent + " > " + loc : loc;
    }

    private String getIdentificationFrame(Engine.WebObjectBeforeAtFrame webObject) {
        if(!webObject.getFrameTagName().isEmpty()){
            return "[id='"+webObject.getIdFrame()+"'] ";
        } else {
            return "[name='"+webObject.getNameFrame()+"'] ";
        }
    }

    private String getParentsNodesOfWebElementByCss(String css, WebElement e){
        List<String> tags = new ArrayList<String>();
        String locationParent = "";
        String a = "return document.querySelector(\""+css+"\").parentElement";
        a = "return arguments[0].parentNode";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElementParent = null;
        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();

        webElementParent = e;

        try {
            boolean exit = false;
            while(exit==false){
                webElementParent = (WebElement) js.executeScript(a, webElementParent);

                if(webElementParent != null)
                    tags.add(webElementParent.getTagName());

            }

        } catch(Exception exceptionn) {
            //exceptionn.printStackTrace();
        }

        if(tags.size() > 1)
            tags = (List<String>) ArrayUtils.shortReverse(tags);

        for(String str : tags)
            if(StringUtils.isNotEmpty(str))
                locationParent += str + ">";

        locationParent = StringUtils.substring(locationParent, 0, locationParent.length()-1);

        return locationParent;
    }


    public String getLocation() {
        return this.location;
    }

    public String getLocationParent() {
        return this.locationParent;
    }

    public WebElement getWebElement() {
        return this.webElement;
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    public void click() {

        System.out.println(this.location);
        System.out.println(this.locationParent);

        // TODO hay que controlar la gestion de frames cuando cojo los webelement en la primera iteracion
        try {
            webElementFrame.click();
        } catch (WebDriverException e){
            webElementFrame.findElement(By.cssSelector(getLocation())).click();
        }
    }

    public void escribir(String keys) {
        webElement.sendKeys(keys);
    }

    public void limpiar() {
        webElement.clear();
    }

    public String getAtribute(String attr) {
        return webElement.getAttribute(attr);
    }

    public void getAtributes(WebDriver e) {
      // TODO
    }

    public void findElement(By by) {
        webElement.findElement(by);
    }

//    public void getLocation() {
//        webElement.getLocation();
//    }

    public void getRect() {
        webElement.getRect();
    }

    public void getSize() {
        webElement.getSize();
    }

    public void getCssValue(String attrCss) {
        webElement.getCssValue(attrCss);
    }

    public String getText() {
//        try {
//            return webElement.getText();
//        } catch (StaleElementReferenceException e){
//            return driver.findElement(By.cssSelector(getLocation())).getText();
//        }
        return this.text;
    }

    public String getTagName() {
        return this.tagname;
    }

    public void isDisplayed() {
        webElement.isDisplayed();
    }
    public void isEnabled() {
        webElement.isEnabled();
    }

    public void isSelected() {
        webElement.isSelected();
    }

//    public int getPositionInSourceCode() {
//        return this.positionInSourceCode;
//    }

    public List<WebElementAttribute> getAttributes() {
        return this.attributes;
    }

    public boolean existAttributeName(String attrName) {
        return getValueByAttrName(attrName) != null;
    }

    public boolean hasValueByNameAttr(String attrName) {
        return existAttributeName(attrName) && StringUtils.isNotEmpty(getValueByAttrName(attrName));
    }

    public WebElementAttribute getWebAttributeByName(String attrName) {
        for(WebElementAttribute attr : this.attributes)
            if(StringUtils.equalsIgnoreCase(attr.getNameAttribute(), attrName))
                return attr;

        return null;
    }

    public String getValueByAttrName(String attrName) {
        WebElementAttribute wa = getWebAttributeByName(attrName);
        return wa != null ? wa.getValueAttribute() : null;
    }

    private List<WebElementAttribute> getAttributesOfWebElement(WebElement e) {
        return getAttrOfWebElementByTagNameAndPosition(e, -1);
    }

    private List<WebElementAttribute> getAttributesOfWebElementWithPos(WebElement e, int pos) {
        return getAttrOfWebElementByTagNameAndPosition(e, pos);
    }

    private List<WebElementAttribute> getAttrOfWebElementByTagNameAndPosition(WebElement e, int position) {
        String a = "";
        if(position != -1)
            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[" + position + "].attributes";
        else
            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[0].attributes";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        ArrayList<Map<String, String>> ae;
        ArrayList<Map<String, WebElement>> ae_webelement;
        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();

        try {
            ae = (ArrayList) js.executeScript(a, e);
            ae_webelement = (ArrayList) js.executeScript(a, e);
            atributos = extraerAtributos(ae, ae_webelement);
        } catch(org.openqa.selenium.WebDriverException exceptionn) {
//            System.out.println("ea");
        }

        return atributos;
    }
    private List<WebElementAttribute> extraerAtributos(ArrayList<Map<String, String>> atributosFromWebElement, ArrayList<Map<String, WebElement>> atributosFromWWebElement){

        List<WebElementAttribute> res_attrs = new ArrayList<WebElementAttribute>();

        for(Map<String, String> attributeMap : atributosFromWebElement){
            String attributeName = attributeMap.get("localName");
            String attributeValue= attributeMap.get("nodeValue");

            res_attrs.add(new WebElementAttribute(attributeName, attributeValue));
        }
        return res_attrs;
    }
}
