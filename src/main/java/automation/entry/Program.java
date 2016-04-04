package automation.entry;

import org.automation.utls.PropertyReader;

import java.io.IOException;

/**
 * Created by shantonu on 4/3/16.
 */
public class Program {
    public static void main(String... args){

        try {
            System.out.println(PropertyReader.getProperty("./src/main/resources/browser.properties","selenium.browser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCurrentDir(){
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.out.println(System.getProperty("maven.test.skip"));
    }
}
