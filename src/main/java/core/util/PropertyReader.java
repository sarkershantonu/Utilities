package core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by shantonu on 4/2/16.
 */
public class PropertyReader {

    private static Properties properties;

    public String getProperty(String nameOfPropertyFile, String nameOfProperty) throws IOException {
        properties = new Properties();

        InputStream its = this.getClass().getClassLoader().getResourceAsStream(nameOfPropertyFile);

        if (its != null) {
            properties.load(its);
        } else {
            throw new FileNotFoundException(nameOfPropertyFile + " Not Found");
        }
        return properties.getProperty(nameOfProperty);
    }
}
