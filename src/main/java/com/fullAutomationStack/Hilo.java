package com.fullAutomationStack;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.sql.Driver;
import java.util.List;

/**
 * Created by lleir on 29/3/18.
 */
public class Hilo extends Thread {

    
    private WebDriver driver;

    public void setWebDriver(WebDriver w) {
        driver = w;
    }


    public void run() {

        //api_selenium a = new api_selenium();

        // driver = a.initChromePrivate();
        try {

            Integer rand = GenericCommon.randomNumberHasta(3);

            String rando = GenericCommon.randomString();

            driver.get("http://www.instagram.com/");

            espera(2000);

            String emailOrPhone = "pepe" + rand + rando + "@gmail.com";
            String fullName = "Jose" + rand + rando + " De la Fuente";
            String username = "Jose" + rand + rando + "Dela";
            String password = "123456Cc.";


            escribir("emailOrPhone", emailOrPhone);
            escribir("fullName", fullName);
            escribir("username", username);
            escribir("password", password);

            click("button:nth-child(2)");

            espera(5000);

            try {
                WebElement activar_notifficaciones = driver.findElement(By.cssSelector("span[text='Activar notificaciones']"));

                if (activar_notifficaciones != null)
                    driver.findElements(By.cssSelector("button[role='button']")).get(1).click();

            } catch (NoSuchElementException e) {


            }

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


            try {
                List<WebElement> link_entrar_sin_login = driver.findElements(By.cssSelector("a"));


                for(WebElement e : link_entrar_sin_login){
                    if(e.getAttribute("href").contains("/accounts/login/")){

                        if(!e.isDisplayed()){
                            mysql_acces mysql = new mysql_acces();
                            mysql.ini();
                            mysql.input(fullName, username, password, emailOrPhone);
                            mysql.close();

                            noHayLinkIniciarSesion = false;

                        }

                        break;

                    }
                }


            } catch (NoSuchElementException e) {
                if (noHayLinkIniciarSesion){
                    mysql_acces mysql = new mysql_acces();
                    mysql.ini();
                    mysql.input(fullName, username, password, emailOrPhone);
                    mysql.close();
                }
            }



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

        } catch (Exception as) {

        }

            driver.quit();

    }

    public void run3() {

        //api_selenium a = new api_selenium();

        // driver = a.initChromePrivate();
        try {
            Integer rand = GenericCommon.randomNumberHasta(3);
            String rando = GenericCommon.randomString();
            driver.get("http://www.instagram.com/");
            espera(500);
            escribir("emailOrPhone", "pepe" + rand + rando + "@gmail.com");
            escribir("fullName", "Jose" + rand + rando + " De la Fuente");
            escribir("username", "Jose" + rand + rando + "Dela");
            escribir("password", "123456Cc.");
            click("button:nth-child(2)");
            espera(500);

        }catch(Exception es){

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
        a.get(1).click();

    }

}
