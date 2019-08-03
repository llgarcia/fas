package com.fullAutomationStack;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 

{

    private static WebDriver driver;

    @Test
    public void test() {

        api_selenium a = new api_selenium();
        a.initChromePrivate();

        driver = a.driver;
        driver.get("http://google.es");


        /** Get SourcePage */
        String pageSource = driver.getPageSource();

        System.out.println(pageSource);

        /** Get all elements in list. */
        List<WebElement> listWebElements = driver.findElements(By.cssSelector("*"));

        List<WebElement> buttons = new ArrayList<WebElement>();
        List<WebElement> divs = new ArrayList<WebElement>();
        List<WebElement> spans = new ArrayList<WebElement>();
        List<WebElement> labels = new ArrayList<WebElement>();
        List<WebElement> inputs = new ArrayList<WebElement>();

        System.out.println("QTT WEB ELEMENTS: " + listWebElements.size());

        for(WebElement e : listWebElements) {

            if(!e.getAttribute("id").isEmpty()) {
                System.out.println("Text: " + e.getAttribute("text"));

                System.out.println("\nID: " + e.getAttribute("id"));
                System.out.println("CLASS: " + e.getAttribute("class"));
                System.out.println("TAGNAME: " + e.getTagName());


                if(e.getTagName().equals("button"))
                    buttons.add(e);

                if(e.getTagName().equals("span"))
                    spans.add(e);

                if(e.getTagName().equals("div"))
                    divs.add(e);

                if(e.getTagName().equals("input"))
                    inputs.add(e);

                if(e.getTagName().equals("label"))
                    labels.add(e);

            }
        }

        System.out.println("Buttons: " + buttons.size());
        System.out.println("Span: " + spans.size());
        System.out.println("Divs: " + divs.size());
        System.out.println("Inputs: " + inputs.size());
        System.out.println("Labels: " + labels.size());

        return;

    }

    @Test
    public void test_unique() {

        try {
           driver = initChromePrivate();

            Integer rand = GenericCommon.randomNumberHasta(3);

            String rando = GenericCommon.randomString();



            driver.get("http://www.instagram.com/");
            espera(2000);
            driver.findElement(By.cssSelector("a[href=\"/accounts/login/\"]")).click();
            espera(2000);

            String emailOrPhone = "lleirgarcia@gmail.com";
            String password = "p4p4LL0n4";

            System.out.println(driver.getPageSource());

            escribir("username", emailOrPhone);
            //escribir("fullName", fullName);
            //escribir("username", username);
            escribir("password", password);
            espera(2000);


            click("button:nth-child(1)");

            espera(5000);

            try {
                WebElement e = driver.findElement(By.cssSelector("p[id='ssfErrorAlert']"));

            } catch(NoSuchElementException e){
               // mysql_acces mysql = new mysql_acces();
               // mysql.ini();
               // mysql.input(fullName, username, password, emailOrPhone);
               // mysql.close();

            }



            /*try {
                WebElement activar_notifficaciones = driver.findElement(By.cssSelector("span[text='Activar notificaciones']"));

                if (activar_notifficaciones != null)
                    driver.findElements(By.cssSelector("button[role='button']")).get(1).click();

            } catch (NoSuchElementException e) {


            }

            */ driver.get("https://www.instagram.com/bluepanda.tattoo/?hl=es");

            espera(500);

           /* try {
                WebElement e = driver.findElement(By.cssSelector("input[name='phone_number']"));
                if (e != null) {
                    e.sendKeys("67" + GenericCommon.randomNumberHasta(7) + "");
                    driver.findElement(By.cssSelector("span > button")).click();
                }

            } catch (NoSuchElementException e) {

            }
*/
          //  List<WebElement> filas = driver.findElements(By.cssSelector("div[class='Nnq7C weEfm']"));
            List<String> jota = new ArrayList<String>();



            int total = 12;

            for(int a = 1; a <= total; a++){
                WebElement imagenes = driver.findElement(By.cssSelector("div[class='Nnq7C weEfm'] > div:nth-child("+a+")"));
                imagenes.click();
                espera(2000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.getElementsByTagName(\"textarea\")[0].value=\"Te quiero\"");
               // js.executeScript("document.getElementsByTagName(\"textarea\")[0].innerHTML = \"Te quiero\"");

                String value = (String) js.executeScript("document.getElementsByTagName(\"textarea\")[0].value");

                        //WebElement aas = (WebElement) js.executeScript("return document.getElementsByTagName(\"form\")[1]");
                //aas.sendKeys(Keys.RETURN);
                driver.findElements(By.cssSelector("form")).get(1).sendKeys(Keys.RETURN);
                //driver.findElement(By.cssSelector("textarea[placeholder='Agrega un comentario...']")).("Te quiero");
                total = total + 12;
            }


            try {
               // System.out.println(filas.get(1).getAttribute("class"));
               // filas.get(1).click();
                espera(1500);

                List<WebElement> articles = driver.findElements(By.cssSelector("article"));
                List<WebElement> section = articles.get(articles.size() - 1).findElements(By.cssSelector("section"));
                List<WebElement> megusta = section.get(0).findElements(By.cssSelector("a[role='button']"));

                megusta.get(0).click();
            } catch (NoSuchElementException | IndexOutOfBoundsException e) {

            }

/*
            List<WebElement> buttons_seguir = driver.findElements(By.cssSelector("main[role='main'] section button"));
            if (buttons_seguir != null)
                buttons_seguir.get(0).click();

            List<WebElement> filas = driver.findElements(By.cssSelector("div[class='_6d3hm _mnav9'] > div"));

            try {
                System.out.println(filas.get(1).getAttribute("class"));
                filas.get(1).click();
                espera(1500);

                List<WebElement> articles = driver.findElements(By.cssSelector("article"));
                List<WebElement> section = articles.get(articles.size() - 1).findElements(By.cssSelector("section"));
                List<WebElement> megusta = section.get(0).findElements(By.cssSelector("a[role='button']"));

                megusta.get(0).click();
            } catch (NoSuchElementException | IndexOutOfBoundsException e) {

            }
*/
        } catch (Exception as) {
            as.printStackTrace();
        }

        driver.quit();


    }


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

    public WebDriver initMozillaDriver() {
        capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("htmlunit");
        optionsF = new FirefoxOptions();

        optionsF.addArguments("--start-maximized");
        optionsF.addArguments("--incognito");
        optionsF.addArguments("test-type");
        optionsF.addArguments("--disable-extendsions");

        File chromeDriver = new File("/Users/lleir/IdeaProjects/fas/recursos/geckodriver");

        System.setProperty("webdriver.gecko.driver", chromeDriver.getAbsolutePath());

        return new FirefoxDriver(optionsF);

    }

    public static WebDriver driver1 = null;
    public static WebDriver driver2 = null;
    public static WebDriver driver3 = null;

    @Test
    public void testHilos() throws Exception {

        List<Hilo> hilos = new ArrayList<Hilo>();

        for (int count = 0; count < 2; count++)
            hilos.add(new Hilo());

        for (Hilo hh : hilos)
            hh.setWebDriver(initChromePrivate());

        for (Hilo hh : hilos)
            hh.start();

        for (Hilo hh : hilos)
            hh.run();

    }

    @Test public void testJ(){
        Hilo l = new Hilo();
        l.setWebDriver(initChromePrivate());

        l.run();
    }

    @Test
    public void testHilos2() throws Exception {

        //generateNames();

        List<TRegistro> hilos = new ArrayList<TRegistro>();

        RouterClass c = new RouterClass();
        mysql_acces mysql_acces = new mysql_acces();

        mysql_acces.ini();

        LinkedHashMap<Integer, List<String>> resultQuery = mysql_acces.selectFromNotRegistrer();

        for(int router_count = 0; router_count < 300; router_count++) {

            System.out.print("rinicios router: " + router_count);
            c.chekcWifi();

            for (int count = 0; count < 13; count++) {
                hilos.add(new TRegistro());
                List<String> row = resultQuery.get(count);
                hilos.get(count).setInstagramNameUserEmail(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4));
            }

            for (TRegistro hh : hilos) {
                hh.setWebDriver(initChromePrivate());

                // cojer los 10 primeros para instanciarlos en cada "thread"
                // luego hacer query ne mismo thread para setear "registrado = 1" en la BBDD

                //hh.setInstagramNameUserEmail();
            }

            for (TRegistro hh : hilos)
                hh.start();

            for (TRegistro hh : hilos)
                hh.run();

            hilos = new ArrayList<TRegistro>();


            if(router_count % 2 == 0){
                System.out.println("multiple de 2: " + router_count);
                mysql_acces.deleteFromNoRegistrado();
                generateNames();
            }


            Runtime rt = Runtime.getRuntime();
            rt.exec("pkill -f Google Chrome");
            rt.exec("pkill -f chromedriver1");
            c.accesRouterUrl(initChromePrivate());
            resultQuery = mysql_acces.selectFromNotRegistrer();

        }

    }

    @Test public void generateNames() {

        WebDriver driver = initChromePrivate();

        driver.get("https://proinf.net/clases/MS-Excel/avanzado2011/generador_nombres/javascript/generador.html");

        Select s = new Select(driver.findElement(By.id("total1")));
        s.selectByIndex(2);

        driver.findElement(By.id("boton1")).click();

        espera(driver, 1000);


        mysql_acces mysql = new mysql_acces();
        mysql.ini();


        List<WebElement> trs = driver.findElements(By.cssSelector("tbody[id='datos1'] > tr"));

        for(WebElement tr : trs){

            List<WebElement> tds = tr.findElements(By.cssSelector("td"));

            String nombre   = tds.get(0).getText();
            String apellido = tds.get(1).getText();

            String nombreAPe = GenericCommon.replaceAccents(nombre + " " + apellido);

            //String municipio= tds.get(4).getText();

            int pos1 = GenericCommon.randomNumberHasta(2);
            int pos2 = GenericCommon.randomNumberHasta(2);
            int pos3 = GenericCommon.randomNumberHasta(2);



            String mail =
                    pos1 + "_"
                    + GenericCommon.replaceAccents(StringUtils.replace(StringUtils.substring(nombre, 0, 3)
                        + "_" +StringUtils.substring(apellido, 0, 3)
                            + "_" + pos2
                                + "_" + pos3, " ", ""));

            String password = "123456Cc.";
            String email    = StringUtils.lowerCase(mail) + "@gmail.com";

            String usuario = nombre + "_" + StringUtils.substring(apellido, 0, 4) + pos1;

            mysql.inputUserNotRegistred(nombreAPe, usuario, password, email);
        }


        mysql.close();
        try {
            //testHilos2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.close();

    }

    @Test
    public void testRouterUrl() {


        RouterClass c = new RouterClass();
        c.accesRouterUrl(initChromePrivate());
        c.chekcWifi();

    }

    @Test
    public void testMysql() {

 //       mysql_acces mysql = new mysql_acces();
 //       mysql.ini();
 //       mysql.input();
 //       mysql.close();

    }

    private void espera(WebDriver e, Integer miliseconds) {
        try {
            synchronized (e) {
                e.wait(miliseconds);
            }
        } catch (InterruptedException ae) {
            ae.printStackTrace();
        }
    }

    private void espera(Integer miliseconds) {
        try {
            synchronized (driver) {
                driver.wait(miliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void escribir(String name, String text) {
        driver.findElement(By.cssSelector("input[name='"+name+"']")).sendKeys(text);
    }

    private void click(String css){
        List<WebElement> a = driver.findElements(By.tagName("button"));
        a.get(0).click();

    }
}
