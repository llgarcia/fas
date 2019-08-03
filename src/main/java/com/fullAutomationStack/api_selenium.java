package com.fullAutomationStack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by lleir on 24/3/18.
 */

public class api_selenium {

    protected static WebDriver driver;
    protected static DesiredCapabilities capabilities;
    protected static ChromeOptions options;


    public void initChromePrivate() {
        //capabilities = DesiredCapabilities.chrome();
        options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extendsions");


        File chromeDriver = new File("/Users/lleir/IdeaProjects/fas/recursos/chromedriver1");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver =  new ChromeDriver(options);
    }

    public void initChromeNormal() {
        capabilities = DesiredCapabilities.chrome();
        options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extendsions");


        File chromeDriver = new File("/Users/lleir/IdeaProjects/fas/recursos/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver(options);
    }

}
