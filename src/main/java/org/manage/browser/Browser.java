package org.manage.browser;


import com.sun.javafx.perf.PerformanceTracker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shantonu on 4/2/16.
 * Maintain Singleton behavior for driver object
 */
public class Browser {

    private final String JS4perf="var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {};var timings = performance.timing || {};return timings;";
    private static WebDriver driver = null;
    private static Map<String, Object> timing = null;

    public static WebDriver getInstance(){
        if(driver==null){
            driver = new BrowserFactory().getABrowser();
            timing = new HashMap<String, Object>();
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

    public static Map<String, Object> getAllNavigationTiming(){

        JavascriptExecutor jsRunner = (JavascriptExecutor) driver;
        timing = (Map<String, Object>)jsRunner.executeAsyncScript(JS4perf);
        return timing;
    }

    public static Long getloadignTime(){
        return getATime("loadEventEnd");
    }
    private static Long getATime(String name){
       return (Long) timing.get(name);
    }
}
