package com.fullAutomationStack;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lleir on 9/4/18.
 */
public class Login {

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

    @Test
    public void testLogin() {
        TLogin t = new TLogin();
        t.setWebDriver(initChromePrivate());
        t.runn();
    }

    @Test
    public void testHilos2() throws Exception {

        //generateNames();

        List<TLogin> hilos = new ArrayList<TLogin>();

        RouterClass c = new RouterClass();
        mysql_acces mysql_acces = new mysql_acces();

        mysql_acces.ini();

        LinkedHashMap<Integer, List<String>> resultQuery = mysql_acces.selectFromRegistrer();

        for(int router_count = 0; router_count < 300; router_count++) {

            System.out.print("rinicios router: " + router_count);
            c.chekcWifi();

            for (int count = 0; count < 5; count++) {
                hilos.add(new TLogin());
                List<String> row = resultQuery.get(count);
                hilos.get(count).setInstagramNameUserEmail(row.get(3), row.get(4));
                hilos.get(count).setUserToGo("lleirgb");
            }

            for (TLogin hh : hilos) {
                hh.setWebDriver(initChromePrivate());

                // cojer los 10 primeros para instanciarlos en cada "thread"
                // luego hacer query ne mismo thread para setear "registrado = 1" en la BBDD

                //hh.setInstagramNameUserEmail();
            }

            for (TLogin hh : hilos)
                hh.start();

            for (TLogin hh : hilos)
                hh.run();

            hilos = new ArrayList<TLogin>();

            Runtime rt = Runtime.getRuntime();
            rt.exec("pkill -f Google Chrome");
            rt.exec("pkill -f chromedriver1");

        }

    }
}
