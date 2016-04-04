package org.automation.utls;

import java.io.*;
import java.util.Properties;

/**
 * Created by shantonu on 4/2/16.
 */
public class PropertyReader {




    private static Properties properties =  new Properties();;
    private static OutputStream output = null;
    private static InputStream input = null;
    private static String outputdir="./";

    public static void setProperty(String nameOfPropertyFile, String nameOfProperty, String valueOfProperty) throws IOException {
        output = new FileOutputStream(nameOfPropertyFile);
        properties.setProperty(nameOfProperty, valueOfProperty);
        properties.store(output,null);
    }
    public static String getProperty(String nameOfPropertyFile, String nameOfProperty) throws IOException{

        input = new FileInputStream(nameOfPropertyFile);
        //if you need to load from class loaders -> Aulternate way
        //InputStream its = this.getClass().getClassLoader().getResourceAsStream(nameOfPropertyFile);
        if (input != null) {
            properties.load(input);
        } else {
            throw new FileNotFoundException(nameOfPropertyFile + " Not Found");
        }
        return  properties.getProperty(nameOfProperty);
    }
}
