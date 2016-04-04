package automation.entry;

import org.automation.utls.PropertyManager;

import java.io.IOException;

/**
 * Created by shantonu on 4/3/16.
 */
public class Program {


    public static void main(String... args){

    }

    private static void printCurrentDir(){
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.out.println(System.getProperty("maven.test.skip"));
    }

    private static void writeAProperty(){
        try {
            PropertyManager.setProperty("./src/main/resources/browser.properties", "test.add.property", System.currentTimeMillis()+"ValueOfOtem");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void readAProperty(){
        try {

            System.out.println(PropertyManager.getProperty("./src/test/resources/testing.properties","browser.default"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchBrowser(){

    }
}
