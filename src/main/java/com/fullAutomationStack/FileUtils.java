package com.fullAutomationStack;

import com.fullAutomationStack.GeneralUtils.UtilityProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lleir on 24/3/18.
 */
public class FileUtils {

    private static String path;
    private static String name;
    private static String extension;

    public static void setPath(String p) {
        path = p;
    }

    public static void setExtension(String ext) {
        extension = ext;
    }

    public static void setName(String nombre){
        name = nombre;
    }

    public static void crearArchivo() {
        crearArchivo(null);
    }

    public static void crearArchivo(String input) {

        String stringfile = path + UtilityProperties.SEPARATOR + name + "."+ extension;
        System.out.println(stringfile);
        File f = new File(stringfile);

        if(f.exists()){
            f.delete();
        }

        try {
            if(f.createNewFile()) {

                FileWriter writer = new FileWriter(f);
                if(input != null)
                    writer.write(input);

                writer.close();

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static void crearDirectorioSiNoExiste(String path, String name) {

        String stringfile = path + "\\" + name;

        File f = new File(stringfile);

        if(!f.exists())
            f.mkdir();

    }


}
