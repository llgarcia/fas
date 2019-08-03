package com.fullAutomationStack.GeneralUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class UtilitySO {

    public static final String OS_NAME     = System.getProperty("os.name");
    public static final String OS_VERSION  = System.getProperty("os.version");
    public static final String SEPARATOR   = System.getProperty("file.separator");
    public static final String USER_DIR    = System.getProperty("user.dir");
    public static final String USER_HOME   = System.getProperty("user.home");
    public static final String USER_NAME   = System.getProperty("user.name");
    public static Properties PROPERTIES;

    public static String transformPath(String path) {
        if(StringUtils.contains(path, SEPARATOR))
            return path;

        else if (StringUtils.contains(path, "/"))
            return StringUtils.replace(path, "/", SEPARATOR);
        else
            return StringUtils.replace(path, "\\", SEPARATOR);


    }

}
