package automation.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by shantonu on 4/2/16.
 */
public class PropertyUtil {

    private static Properties properties =  new Properties();
    private static OutputStream output = null;
    private static InputStream input = null;
    private static String outputdir="./";

    /**
     * Avoid using this as we will set property from CLI or Jenkins
     * @param nameOfPropertyFile
     * @param nameOfProperty
     * @param valueOfProperty
     * @throws IOException
     */
    public static void setProperty(String nameOfPropertyFile, String nameOfProperty, String valueOfProperty) throws IOException {
        output = new FileOutputStream(nameOfPropertyFile, true);//append mode

        if(getProperty(nameOfPropertyFile,nameOfProperty)!=null) {
            properties.setProperty(nameOfProperty, valueOfProperty);
            properties.store(output, "Added at "+System.currentTimeMillis());
        }
        else
        {
        }

        output.close();


    }
    public static String getProperty(String nameOfPropertyFile, String nameOfProperty) throws IOException{
        input = new FileInputStream(nameOfPropertyFile);
        //if you need to load from class loaders -> Aulternate way
        //InputStream its = this.getClass().getClassLoader().getResourceAsStream(nameOfPropertyFile);
        if (input != null) {
            properties.load(input);
            input.close();
        } else {
            throw new FileNotFoundException(nameOfPropertyFile + " Not Found");
        }
        return  properties.getProperty(nameOfProperty);
    }

}
