package com.fullAutomationStack;

import com.fullAutomationStack.GenericCommon;
import com.fullAutomationStack.mysql_acces;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

/**
 * Created by lleir on 8/4/18.
 */
public class TLogin extends Thread{

    protected static DesiredCapabilities capabilities;
    protected static ChromeOptions options;

    private WebDriver driver;

    public void setWebDriver(WebDriver w) {
        driver = w;
    }

    private int id;
    private String emailOrPhonee;
    private  String fullNamee;
    private String usernamee;
    private String passwordd;

    public String userToGo = "https://www.instagram.com/";

    public void setUserToGo(String user) {
        userToGo += user + "/";
    }

    public void setInstagramNameUserEmail(String passworddd, String email) {
        emailOrPhonee = email;
        passwordd = passworddd;
    }


    public void run() {


        //api_selenium a = new api_selenium();

        // driver = a.initChromePrivate();
       runn();



    }


    public void runn() {
        try {

            driver.get("https://www.instagram.com/accounts/login/");

            espera(2000);
            escribir("username", emailOrPhonee);
            escribir("password", passwordd);

            List<WebElement> react = driver.findElements(By.cssSelector("span > button"));

            react.get(0).click();

            espera(2000);

            driver.get(userToGo);

            try {
                WebElement activar_notifficaciones = driver.findElement(By.cssSelector("span[text='Activar notificaciones']"));

                if (activar_notifficaciones != null)
                    driver.findElements(By.cssSelector("button[role='button']")).get(1).click();

            } catch (NoSuchElementException e) {


            }

            driver.get(userToGo);


            List<WebElement> buttons_seguir = driver.findElements(By.cssSelector("main[role='main'] section button"));
            if (buttons_seguir != null)
                buttons_seguir.get(0).click();


/*
            driver.get("https://www.instagram.com/bluepanda.tattoo/?hl=es");

            espera(500);

            try {
                WebElement e = driver.findElement(By.cssSelector("input[name='phone_number']"));
                if (e != null) {
                    e.sendKeys("67" + GenericCommon.randomNumberHasta(7) + "");
                    driver.findElement(By.cssSelector("span > button")).click();
                }

            } catch (NoSuchElementException e) {

            }

            espera(500);

            boolean noHayLinkIniciarSesion = true;


            */

            driver.quit();

        } catch (Exception as) {
            as.printStackTrace();
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

    private void escribirCss(String cssSelector, String text) {
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    private void escribir(String name, String text) {
        driver.findElement(By.cssSelector("input[name='"+name+"']")).sendKeys(text);
    }

    private void click(String css){
        List<WebElement> a = driver.findElements(By.tagName("button"));
        a.get(1).click();

    }

}
