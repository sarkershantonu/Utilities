package org.manage.browser;

import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/2/16.
 * Maintain Singleton behavior for driver object
 */
public class Browser {

    private static WebDriver driver = null;

    public static WebDriver getInstance(){
        if(driver==null){
            driver = new BrowserFactory().getABrowser();
        }
        return driver;
    }

    private Browser(){}

    public static void goBack(){
        driver.navigate().back();
    }
    public static void goForward(){
        driver.navigate().forward();
    }
    public static void refresh(){
        driver.navigate().refresh();

    }
    public static void quit(){
        driver.quit();// necessary for browser stack
    }
    private static void initiDriver(){
        //// TODO: initation of driver items like screen resolation, timeout etc.  
    }
}
