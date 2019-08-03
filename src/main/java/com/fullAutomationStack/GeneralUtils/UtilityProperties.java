package com.fullAutomationStack.GeneralUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityProperties extends UtilitySO{

    private static String pathProperties = transformPath("target\\classes\\");

    public static void loadInit() {
        loadProperties("init.properties");
    }

    public static void loadProperties(String fileProperties) {

        String file = pathProperties + fileProperties;

        if(PROPERTIES == null)
            PROPERTIES = new Properties();

        try {
            PROPERTIES.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO hay que ahcer un mapa de archivos YA cargados, para no volverlos a cargar. no?
    private static boolean isFileLoaded(String pathProperties) {
        // todo
        return false;
    }

    public static String getPropertie(String key) {
        if(PROPERTIES != null) {
            String res = PROPERTIES.getProperty(key);

            if (res != null)
                return res;
            else {
                System.out.println("No property founded.");
                return "";
            }
        } else {
            System.out.println("Properties is null. Not load.");
            return "";
        }
    }

}
