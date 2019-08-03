package com.fullAutomationStack;

import com.fullAutomationStack.GeneratorUtils.Engines.Engine;
import com.fullAutomationStack.GeneratorUtils.GenerarClass;
import com.fullAutomationStack.GeneratorUtils.Engines.PoolWebObjectList;
import com.fullAutomationStack.GeneratorUtils.WebObjectList;
import com.fullAutomationStack.WebDriver.WebElementAttribute;
import com.fullAutomationStack.WebDriver.WebElementObject;
import com.fullAutomationStack.parser.JavascriptWebElement;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.*;

/**
 * Created by lleir on 1/6/18.
 */
public class TestWebElementFrameworkClass {

    private WebDriver driver;

    protected static DesiredCapabilities capabilities;
    protected static ChromeOptions optionsC;
    protected static FirefoxOptions optionsF;



    public WebDriver initChromePrivate() {
        capabilities = DesiredCapabilities.htmlUnit();
        capabilities.setBrowserName("htmlunit");
        optionsC = new ChromeOptions();

        optionsC.addArguments("--start-maximized");
        optionsC.addArguments("--incognito");
        optionsC.addArguments("test-type");
        optionsC.addArguments("disable-infobars");
        optionsC.addArguments("--disable-extendsions");
        optionsC.addArguments("--disable-cache");

        File chromeDriver = new File("/Users/lleir/IdeaProjects/fas/recursos/chromedriver1");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());

        return new ChromeDriver(optionsC);

    }

    @Test public void testEngine() {
        driver = initChromePrivate();
//        driver.get("http://www.google.com/");
//        driver.get("https://www.google.com/intl/es/gmail/about/#");
driver.get("http://www.gwapauat-es.inithealth.com/legal/");
        Engine e = new Engine(driver);

        e.setWo_list(new WebObjectList());
        e.espera(5000);
        e.getAllWebElementsOfPage();
        e.removeTagsDontUse();

        PoolWebObjectList.addWebObjectList(e.getWo_list());

        e.generateClass("GeneratorClassWebElementSuperRefactor");
//        e.searchAllAnchorsRecursive(null, -1);
    }

//    @Test
//    public void testclasswebelementobject() throws Exception {
//        driver = initChromePrivate();
////        driver.get("https://www.instagram.com/accounts/login/");
//        driver.get("http://www.google.com/");
//
//        wo_list = new WebObjectList();
//
//        espera(10000);
//        getAllWebElementsOfPage();
//
//        removeScriptsTag();
//        removeLinkTags();
//        removeMetaTags();
//        removeHtmlTags();
//        removeHeadTags();
//        removeTitleTags();
//        removeStyleTags();
//        removeBodyTags();
//        removeOptionTags();
//        removeLiTags();
//
//        PoolWebObjectList.addWebObjectList(wo_list);
//
//        int count = 0;
////        for(WebElementObject o : wo_list.getWo_list()){
////            System.out.println(count);
////            System.out.println("Location: " + o.getLocation());
////            for(WebElementAttribute a : o.getAttributes()){
////                System.out.println("Attr: " + a.getNameAttribute() + " / " +a.getValueAttribute());
////            }
////
////            System.out.println("****");
////            count++;
////        }
//
//        GenerarClass a = new GenerarClass();
//        a.generateClassWebElementsSuperRefactor(wo_list.getWo_list());
//
//
//        // printar todos los web elements con todos los atributos en formato html!!!!
//
////        Engine.searchAllAnchors();
//
//        try {
//
//            espera(2000);
//            //WebElementObject e = new WebElementObject(driver, driver.findElement(By.cssSelector("input[name='username']")));
//            //e.getAtributes(driver);
//        } catch (Exception e){
//            driver.quit();
//        }
//        driver.quit();
//
//    }

    private List<WebElementAttribute> extraerAtributos(ArrayList<Map<String, String>> atributosFromWebElement, ArrayList<Map<String, WebElement>> atributosFromWWebElement){

        List<WebElementAttribute> res_attrs = new ArrayList<WebElementAttribute>();

        for(Map<String, String> attributeMap : atributosFromWebElement){
            String attributeName = attributeMap.get("localName");
            String attributeValue= attributeMap.get("nodeValue");

            res_attrs.add(new WebElementAttribute(attributeName, attributeValue));
        }

      //  for(Map<String, WebElement> atributeMapWb : atributosFromWWebElement){
      //      WebElement ownerParent   = (WebElement) atributeMapWb.get("ownerElement");
      //    WebElement offsetParent  = (WebElement) atributeMapWb.get("offsetParent");

            // si el elemento es el actual, vuelvo a recoger sus atributos en "ownerParent
      //      if(ownerParent != null && offsetParent == null){

      //       }

//            System.out.printf("a");

        // }

        return res_attrs;
    }



//    // listado de web elements para generar un path css
//    private List<String> webElementPaths = new ArrayList<>();

//    private String getParentsNodesOfWebElementByCss(String css, WebElement e){
//        List<String> tags = new ArrayList<String>();
//        String locationParent = "";
//        String a = "return document.querySelector(\""+css+"\").parentElement";
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement webelement = null;
//        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();
//
//        System.out.println("++++++ "+ css);
//        try {
//            webelement = (WebElement) js.executeScript(a, e);
//            if(webelement != null) {
//                tags.add(webelement.getTagName());
//                locationParent = webelement.getTagName();
//            }
//
//        } catch(org.openqa.selenium.WebDriverException exceptionn) {
//            //exceptionn.printStackTrace();
//        }
//
//
//        return locationParent;
//    }

//    // TODO esto se queda en stand by hay que buscarlo por JS
//    private int getParentsNodesOfWebElement(WebElement e, int position){
//
//        String a = "";
//        if(position != -1)
//            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[" + position + "].parentElement";
//        else
//            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[0].parentElement";
//
//        // parent element devuelve el elemento entero
////        a = "return document.getElementsByTagName(\"div\")[1].parentElement;";
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement webelement = null;
//        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();
//
//        try {
//            webelement = (WebElement) js.executeScript(a, e);
//        } catch(org.openqa.selenium.WebDriverException exceptionn) {
//
//        }
//
//
//
//
//     //   List<WebElement> list = e.findElements(By.cssSelector("*"));
//     //   return list.size();
//        return 0;
//    }

//    private List<WebElementAttribute> getAttributesOfWebElement(WebElement e) {
//        return getAttrOfWebElementByTagNameAndPosition(e, -1);
//    }
//
//    private List<WebElementAttribute> getAttributesOfWebElementWithPos(WebElement e, int pos) {
//        return getAttrOfWebElementByTagNameAndPosition(e, pos);
//    }

//    private List<WebElementAttribute> getAttrOfWebElementByTagNameAndPosition(WebElement e, int position) {
//        String a = "";
//        if(position != -1)
//            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[" + position + "].attributes";
//        else
//            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[0].attributes";
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        ArrayList<Map<String, String>> ae;
//        ArrayList<Map<String, WebElement>> ae_webelement;
//        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();
//
//        try {
//            ae = (ArrayList) js.executeScript(a, e);
//            ae_webelement = (ArrayList) js.executeScript(a, e);
//            atributos = extraerAtributos(ae, ae_webelement);
//        } catch(org.openqa.selenium.WebDriverException exceptionn) {
////            System.out.println("ea");
//        }
//
//        return atributos;
//
//    }

//    private List<JavascriptWebElement> js_webelements = new ArrayList<>();
//
//    private void getAllWebElementsOfPageWithJavascript() {
//
//        String a = "return document.getElementsByTagName(\"html\")[0].childNodes";
//        ArrayList<Map<String, String>> atributos_string;
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        atributos_string = (ArrayList) js.executeScript(a);
//
//        for(Map<String, String> as : atributos_string){
//
//            String attributeName = as.get("localName");
//            String ownerDocument =  as.get("");
//        }
//
//        System.out.println("a");
//
//        // buscar el primer elemento
//        // body?
//
//
//
//    }



}
