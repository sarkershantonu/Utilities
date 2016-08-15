package org.automation.utils.common;


import java.io.*;
import java.util.Properties;
import java.util.StringJoiner;

public class PropertyUtil {

    private static Properties prop =new Properties();;
    private static String propertyRoot = "/src/main/resources/";
    private static OutputStream output = null;
    private static InputStream input = null;

    public static String getSysProperty( String sysProperty) {
        return System.getProperty(sysProperty);
    }

    public static void setSysProperty( String name,  String value) {
        System.setProperty(name, value);
    }

    public static String getSysProperty( String propertyFileName,  String sysProperty) {
        return System.getProperty(sysProperty);
    }

    public static void setProperty(String nameOfPropertyFile, String nameOfProperty, String valueOfProperty) throws IOException {
        output = new FileOutputStream(nameOfPropertyFile, true);//append mode

        if(getProperty(nameOfPropertyFile,nameOfProperty)!=null) {
            prop.setProperty(nameOfProperty, valueOfProperty);
            prop.store(output, "Added at "+System.currentTimeMillis());
        }
        else
        {
        }
        output.close();
    }

    public static String read(String propertyFileName, String name) {
        try {
            prop = read(propertyFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(name);
    }

    public static Properties read(String propertyFileName) throws IOException {
        prop = new Properties();
        File currentDirFile = new File(getSysProperty("user.dir"));
        String rootFolder = currentDirFile.getAbsolutePath();
        InputStream input = new FileInputStream(rootFolder + propertyRoot + propertyFileName);
        prop.load(input);
        input.close();
        return prop;
    }
    public static String getProperty(String nameOfPropertyFile, String nameOfProperty) throws IOException{
        input = new FileInputStream(nameOfPropertyFile);
        //if you need to load from class loaders -> Aulternate way
        //InputStream its = this.getClass().getClassLoader().getResourceAsStream(nameOfPropertyFile);
        if (input != null) {
            prop.load(input);
            input.close();
        } else {
            throw new FileNotFoundException(nameOfPropertyFile + " Not Found");
        }
        return  prop.getProperty(nameOfProperty);
    }
    public static String getSeperator(){
        return PropertyUtil.getSysProperty("file.separator");
    }
    
}
