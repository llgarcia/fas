package com.fullAutomationStack;

import com.mysql.cj.mysqla.authentication.MysqlaAuthenticationProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;

/**
 * Created by lleir on 29/3/18.
 */
public class TRegistro extends Thread{

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

    public void setInstagramNameUserEmail(int iduser, String nombreCompleto, String usernameee, String passworddd, String email) {
        id = iduser;
        emailOrPhonee = email;
        fullNamee = nombreCompleto;
        usernamee = usernameee;
        passwordd = passworddd;
    }


    public void run() {


        //api_selenium a = new api_selenium();

        // driver = a.initChromePrivate();
        try {

            Integer rand = GenericCommon.randomNumberHasta(3);

            String rando = GenericCommon.randomString();

            driver.get("http://www.instagram.com/");

            espera(2000);

            escribir("emailOrPhone", emailOrPhonee);
            escribir("fullName", fullNamee);
            escribir("username", usernamee);
            escribir("password", passwordd);
            //usernamee = driver.findElement(By.cssSelector("input[name='username']")).getText();

            click("button:nth-child(2)");

            espera(5000);

            try {
                WebElement e = driver.findElement(By.cssSelector("p[id='ssfErrorAlert']"));

            } catch(NoSuchElementException e){
                mysql_acces mysql = new mysql_acces();
                mysql.ini();
                mysql.input(fullNamee, usernamee, passwordd, emailOrPhonee);
                mysql.update(id);
                mysql.close();

            }

            try {
                WebElement activar_notifficaciones = driver.findElement(By.cssSelector("span[text='Activar notificaciones']"));

                if (activar_notifficaciones != null)
                    driver.findElements(By.cssSelector("button[role='button']")).get(1).click();

            } catch (NoSuchElementException e) {


            }

            List<WebElement> buttons_seguir = driver.findElements(By.cssSelector("main[role='main'] section button"));
            if (buttons_seguir != null)
                buttons_seguir.get(0).click();

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

    private void escribir(String name, String text) {
        driver.findElement(By.cssSelector("input[name='"+name+"']")).sendKeys(text);
    }

    private void click(String css){
        List<WebElement> a = driver.findElements(By.tagName("button"));
        a.get(1).click();

    }
}
