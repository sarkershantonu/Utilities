package org.browser.manage;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

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


    public static void quit(){
        driver.quit();// necessary for browser stack
    }

    public static void savePageAs(){

    }

    /**
     * Size of screen
     * Timeout
     * All waiting
     */
    private static void initiDriver(){
        // TODO: initation of driver items like screen resolation, timeout etc.
    }


}
