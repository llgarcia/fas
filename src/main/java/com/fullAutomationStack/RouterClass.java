package com.fullAutomationStack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.java2d.pipe.DrawImage;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lleir on 7/4/18.
 */
public class RouterClass {


    WebDriver webDriver;

    public void accesRouterUrl(WebDriver w) {

        webDriver = w;

        webDriver.get("http://192.168.1.1/");

        webDriver.findElement(By.id("Frm_Username")).sendKeys("1234");
        webDriver.findElement(By.id("Frm_Password")).sendKeys("1234");
        webDriver.findElement(By.id("LoginId")).click();

        WebElement ae = webDriver.findElement(By.id("mainFrame"));

        WebDriver e;
        e = webDriver.switchTo().frame(ae);

        try {
            e.findElement(By.id("mmManager")).click();

            espera(2500);

            e.findElement(By.id("smSysMgr")).click();

            espera(2000);

            e.findElement(By.id("Submit1")).click();

            espera(1000);

            e.findElement(By.id("msgconfirmb")).click();
        } catch(Exception es){
            accesRouterUrl(w);
        }

        espera(60000);  // espera de un minuto para que asegurarnos que no haya internet
        //espera(240000); 4 minutos



    }



    private void espera(Integer miliseconds) {
        try {
            synchronized (webDriver) {
                webDriver.wait(miliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chekcWifi() {

        boolean internet_connection = false;

        while(internet_connection==false) {
            try {
                URL url = new URL("http://www.google.com");

                URLConnection connection = url.openConnection();
                connection.connect();

                internet_connection = true;
                System.out.println("Internet Connected");

            } catch (Exception e) {
                //System.out.println("Sorry, No Internet Connection");

            }
        }

    }

}
