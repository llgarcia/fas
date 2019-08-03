package com.fullAutomationStack.GeneratorUtils.Engines;

import com.fullAutomationStack.GeneratorUtils.ArrayUtils;
import com.fullAutomationStack.GeneratorUtils.GenerarClass;
import com.fullAutomationStack.GeneratorUtils.WebObjectList;
import com.fullAutomationStack.WebDriver.WebElementAttribute;
import com.fullAutomationStack.WebDriver.WebElementObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lleir on 24/7/18.
 */
public class Engine {
    @Getter @Setter
    private WebObjectList wo_list;
    private List<WebElement> firstIterationOfWebElemets;
    private List<WebElement> firstIterationOfWebElemetsIframe;

    @Getter @Setter public WebDriver driver;

    private String lastUrl;
    private WebObjectList lastWo_list;

    private int classCounter = 0;

    public Engine(WebDriver driver) {
        this.driver = driver;
    }

    /* TODO aqui hay que... la IDEA es tener un arbol de URLs, activas(que haya entrado) y no activas(que no haya entrado)
           con lo cual se debe de bajar abajo del toodo para poder volver a subir hasta un 'link' que no se haya activado.
        1- ir recorriendo los HREFs
        2- almacenar link actual
        2- entrar en el link
        3- generar pagina
        4- guardar link como activo
        5- revisar si hay mas links e ir entrando, hacer lo mismo
        6- si no hay mas links, window.manage().before() o algo así!!

         */
    public void searchAllAnchors(WebDriver driver) {
        WebObjectList webObjectsSite1 = PoolWebObjectList.getWebObjectsLists().get(0).getWebObjectsByTagNameAndAttribute("a", "href");
        List<WebElementObject> list = webObjectsSite1.getWo_list();
        int count = 0;

        for(WebElementObject wo: list){
            wo.click();
            espera(10000);
            getAllWebElementsOfPage();

            removeScriptsTag();
            removeLinkTags();
            removeMetaTags();
            removeHtmlTags();
            removeHeadTags();
            removeTitleTags();
            removeStyleTags();
            removeBodyTags();
            removeOptionTags();
            removeLiTags();
            PoolWebObjectList.addWebObjectList(wo_list);
            count++;
            new GenerarClass().generateClassWebElementsSuperRefactor(wo_list.getWo_list(), "GeneratorClassWebElementSuperRefactor" + count);
            list = PoolWebObjectList.getWebObjectsLists().get(count).getWebObjectsByTagNameAndAttribute("a", "href").getWo_list();
        }
    }

    // TODO hay que hacer un objeto o variable global (aqui) que guarde e identifique por que LINK vamos

    private boolean isTheSameWebPage(String lastURL, WebObjectList webObjectList) {
        boolean res = false;
        if(driver.getCurrentUrl().equals(lastUrl)) {
            if(wo_list.size() == lastWo_list.size())
                res = true;
        }
        return res;
    }

    public void searchAllAnchorsRecursive(WebObjectList webObjectAnchorList) {
        WebObjectList currentWebObjectListOfAnchorswithHref =
                webObjectAnchorList == null ? PoolWebObjectList.getWebObjectByPosAndTagAndAttribute(0, "a", "href") : webObjectAnchorList;

        lastUrl = driver.getCurrentUrl();
        lastWo_list = PoolWebObjectList.getLastWebObjectList();
        wo_list = webObjectAnchorList == null ? new WebObjectList() : null;

        int count = 0;

        // TODO se ha de controlar el caso de que el link clickado no rediriga a nongun sitio.... controlar esto
        // TODO se sabe que no se ha redireccionado ni ha cambiado nada de la pagina cuando:
            // 1- la URL es la misma
            // 2- el listado de webOjbects tiene el mismo tamaño

            // exceptions
            // 1. se puede dar el caso de que la URL y la cantidad de objetos sea identica?????

        if(!isTheSameWebPage(lastUrl, lastWo_list)) {
            for(int index = 0; index > currentWebObjectListOfAnchorswithHref.size(); index++){
//            for (WebElementObject wo : currentWebObjectListOfAnchorswithHref.getWo_list()) {
                WebElementObject wo = currentWebObjectListOfAnchorswithHref.get(index);
                wo.click();
                espera(10000);
                getAllWebElementsOfPage();

                removeScriptsTag();
                removeLinkTags();
                removeMetaTags();
                removeHtmlTags();
                removeHeadTags();
                removeTitleTags();
                removeStyleTags();
                removeBodyTags();
                removeOptionTags();
                removeLiTags();
                PoolWebObjectList.addWebObjectList(wo_list);
                count++;
                new GenerarClass().generateClassWebElementsSuperRefactor(wo_list.getWo_list(), "GeneratorClassWebElementSuperRefactor" + count);
                currentWebObjectListOfAnchorswithHref = PoolWebObjectList
                        .getWebObjectByPosAndTagAndAttribute(count, "a", "href");
                searchAllAnchorsRecursive(currentWebObjectListOfAnchorswithHref);
            }
        } else {

        }
    }

    public void searchAllAnchorsRecursive(WebObjectList webObjectAnchorList, int indexListWebObjectAnchor) {
        WebObjectList currentWebObjectListOfAnchorswithHref =
                webObjectAnchorList == null ? PoolWebObjectList.getWebObjectByPosAndTagAndAttribute(0, "a", "href") : webObjectAnchorList;

        lastUrl = driver.getCurrentUrl();
        lastWo_list = PoolWebObjectList.getLastWebObjectList();
        wo_list = webObjectAnchorList == null ? new WebObjectList() : wo_list;

        indexListWebObjectAnchor = -1 == indexListWebObjectAnchor ? 0 : indexListWebObjectAnchor;
        int count = 0;
        boolean breaking = false;

        if(!isTheSameWebPage(lastUrl, lastWo_list) || indexListWebObjectAnchor != 0) {
            for(int index = indexListWebObjectAnchor; index < currentWebObjectListOfAnchorswithHref.size(); index++){
                WebElementObject wo = currentWebObjectListOfAnchorswithHref.get(index);

                System.out.println("CUR " + driver.getCurrentUrl());
                System.out.println("HRF " + wo.getWebAttributeByName("href"));

                if(!driver.getCurrentUrl().contains(wo.getAtribute("href"))){
                    wo.click();
                    espera(5000);
                    getAllWebElementsOfPage();

                    removeTagsDontUse();

                    PoolWebObjectList.addWebObjectList(wo_list);
                    count++;
                    classCounter++;
                    generateClass("GeneratorClassWebElementSuperRefactor" + classCounter);
                    currentWebObjectListOfAnchorswithHref = PoolWebObjectList.getWebObjectByPosAndTagAndAttribute(count, "a", "href");

                    indexListWebObjectAnchor = index;
                    searchAllAnchorsRecursive(currentWebObjectListOfAnchorswithHref, -1);
                } else {
                    breaking = true;
                    break;
                }
            }

            if(breaking){
                indexListWebObjectAnchor = indexListWebObjectAnchor + 1;
                searchAllAnchorsRecursive(currentWebObjectListOfAnchorswithHref, indexListWebObjectAnchor);
            }

        } else {
            indexListWebObjectAnchor = indexListWebObjectAnchor + 1;
            searchAllAnchorsRecursive(currentWebObjectListOfAnchorswithHref, indexListWebObjectAnchor);
        }
    }

    public void removeTagsDontUse() {
        removeScriptsTag();
        removeLinkTags();
        removeMetaTags();
        removeHtmlTags();
        removeHeadTags();
        removeTitleTags();
        removeStyleTags();
        removeBodyTags();
        removeOptionTags();
        removeLiTags();
    }

    public void generateClass(String className){
        new GenerarClass().generateClassWebElementsSuperRefactor(wo_list.getWo_list(), className);
    }
    
    private boolean isTagAlredyMapped(List<Integer> pos){
        return (pos != null);
    }

    private boolean isAFrame(WebElement e) {
        return StringUtils.contains(e.getTagName(), "iframe");
    }

    public void getAllWebElementsOfPage() {
        
        firstIterationOfWebElemets = driver.findElements(By.cssSelector("*"));
        firstIterationOfWebElemetsIframe = new ArrayList<WebElement>();

        List<WebElement> res = firstIterationOfWebElemets;

        WebDriver frameDriver     = null;
        WebElement framew   = null;

        Map<String, List<Integer>> mapTagnamesWithPositions = new HashMap<String, List<Integer>>();

        List<Integer> positions;

        WebElementObject weo = null;

        for(WebElement e : res) {
            // declara una "key" segun el tag para ir almacenando los mismos elementos de ese WebElement en el Map.
            // TODO quiero almacenar en este MAP todo el path, por ejemplo: html>body>div>div para así poder saber bien las posiciones del
            // nth-child de cada elemento que hay dentro de esto.
            // el problema es que cuando quiero ver si existe o no, el elemento que tengo tan solo tiene u ntag name,
            // deberia de "estudiarlo" para recoger todo su padre (tengo funcion)

            String webElementCurrentPath = getParentsNodesOfWebElementByCss(null, e);
            webElementCurrentPath = StringUtils.isNotEmpty(webElementCurrentPath) ? webElementCurrentPath + " > " + e.getTagName() : e.getTagName();
            positions = mapTagnamesWithPositions.get(webElementCurrentPath);

            System.out.println("Current " + webElementCurrentPath);
            System.out.println("Tag " + e.getTagName());

            if (isTagAlredyMapped(positions)) {
                int lastPositionOfWebElementByTagInMap = (positions.get(positions.size() - 1)) + 1;
                int nextPositionOfWebElementByTabInMap = (lastPositionOfWebElementByTagInMap + 1);
                weo = wo_list.addToWoList(new WebElementObject(driver, e, lastPositionOfWebElementByTagInMap, nextPositionOfWebElementByTabInMap));
                mapTagnamesWithPositions.get(weo.getPath()).add(lastPositionOfWebElementByTagInMap);
            } else {
                List<Integer> posiciones = new ArrayList<Integer>();
                posiciones.add(0);
                weo = wo_list.addToWoList(new WebElementObject(driver, e));
                mapTagnamesWithPositions.put(weo.getPath(), posiciones);
            }

            if (isAFrame(e)) {

                String frameTagName = e.getTagName();
                String frameId      = e.getAttribute("id");
                String frameName    = e.getAttribute("name");
                String customFrameName = frameTagName+frameId+frameName;

                WebObjectBeforeAtFrame woaf = new WebObjectBeforeAtFrame(frameTagName, frameId, frameName, e.getText());

                // TODO hay que volver a resetear el frame, sino, no se encontrara en futuras busquedas.
                frameDriver = driver.switchTo().frame(e);

                firstIterationOfWebElemetsIframe = frameDriver.findElements(By.cssSelector("*"));

                for (WebElement inFrame : firstIterationOfWebElemetsIframe) {
                    String frameIdentification = frameTagName + woaf.getIdentificationFrame();
                    webElementCurrentPath = frameIdentification + getParentsNodesOfWebElementByCss(null, inFrame);
                    webElementCurrentPath = StringUtils.isNotEmpty(webElementCurrentPath) ? webElementCurrentPath + " > " + inFrame.getTagName() : inFrame.getTagName();
                    positions = mapTagnamesWithPositions.get(webElementCurrentPath);

                    if (isTagAlredyMapped(positions)) {
                        int lastPositionOfWebElementByTagInMap = (positions.get(positions.size() - 1) + 1);
                        int nextPositionOfWebElementByTabInMap = (lastPositionOfWebElementByTagInMap + 1);

                        weo = wo_list.addToWoList(new WebElementObject(frameDriver, e, inFrame, lastPositionOfWebElementByTagInMap, nextPositionOfWebElementByTabInMap, woaf));
                        mapTagnamesWithPositions.get(weo.getPath()).add(lastPositionOfWebElementByTagInMap);

                    } else {
                        List<Integer> posiciones = new ArrayList<Integer>();
                        posiciones.add(0);
                        weo = wo_list.addToWoList(new WebElementObject(frameDriver, e, inFrame, woaf));
                        mapTagnamesWithPositions.put(weo.getPath(), posiciones);
                    }
                }

                driver.switchTo().parentFrame();
            }
        }
    }

    public class WebObjectBeforeAtFrame{

        @Getter private String frameTagName;
        @Getter private String idFrame;
        @Getter private String nameFrame;
        @Getter private String text;

        public WebObjectBeforeAtFrame(String frameTagName, String idFrame, String nameFrame, String text) {
            this.frameTagName = frameTagName;
            this.idFrame = idFrame;
            this.nameFrame = nameFrame;
            this.text = text;
        }

        public String getIdentificationFrame() {
            if(frameTagName.isEmpty()){
                return "[id='"+getIdFrame()+"'] ";
            } else {
                return "[name='"+getNameFrame()+"'] ";
            }
        }
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

    private String getParentsNodesOfWebElementByCss(String css, WebElement e){
        List<String> tags = new ArrayList<String>();
        String locationParent = "";
//        String a = "return document.querySelector(\""+css+"\").parentElement";
//        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();
        String a = "return arguments[0].parentNode";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElementParent = null;


        webElementParent = e;

        try {
            boolean exit = false;
            while(exit==false){
                webElementParent = (WebElement) js.executeScript(a, webElementParent);
                if(webElementParent != null) {
                    tags.add(webElementParent.getTagName());
                    //locationParent = webElementParent.getTagName();
                }

            }

        } catch(org.openqa.selenium.WebDriverException exceptionn) {
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

    // TODO esto se queda en stand by hay que buscarlo por JS
    private int getParentsNodesOfWebElement(WebElement e, int position){

        String a = "";
        if(position != -1)
            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[" + position + "].parentElement";
        else
            a = "return document.getElementsByTagName(\"" + e.getTagName() + "\")[0].parentElement";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webelement = null;
        List<WebElementAttribute> atributos = new ArrayList<WebElementAttribute>();

        try {
            webelement = (WebElement) js.executeScript(a, e);
        } catch(org.openqa.selenium.WebDriverException exceptionn) {

        }

        return 0;
    }

    public void removeScriptsTag() {
        System.out.println("Total " + wo_list.getWo_list().size());
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "script")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Scripts " + wo_list.size());
    }

    public void removeLinkTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "link")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Links " + wo_list.size());
    }

    public void removeMetaTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "meta")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Meta " + wo_list.size());
    }

    public void removeHtmlTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "html")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Html " + wo_list.size());
    }

    public void removeHeadTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "head")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove head " + wo_list.size());
    }

    public void removeTitleTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "title")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Titles " + wo_list.size());
    }

    public void removeStyleTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "style")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Style " + wo_list.size());
    }

    public void removeBodyTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "body")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove body " + wo_list.size());
    }

    public void removeOptionTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "option")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Options " + wo_list.size());
    }

    public void removeLiTags() {
        for(int pos = 0; pos < wo_list.size(); pos++) {
            if (StringUtils.equals(wo_list.get(pos).getTagName(), "li")) {
                wo_list.remove(pos);
                pos--;
            }
        }
        System.out.println("Remove Li " + wo_list.size());
    }

    public void espera(Integer miliseconds) {
        try {
            synchronized (driver) {
                driver.wait(miliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
