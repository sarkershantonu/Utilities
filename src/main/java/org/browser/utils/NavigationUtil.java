package org.browser.utils;

import automation.utils.UtilBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shantonu on 4/10/16.
 */
public class NavigationUtil extends UtilBase {

    private Map<String, Object> timing = null;
    private static final String JS4perf="var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {};var timings = performance.timing || {};return timings;";
    public WebDriver driver = null;
    public NavigationUtil(WebDriver aDriver){
        super(aDriver);
    }
    public void goBack(){
        driver.navigate().back();
    }
    public void goForward(){
        driver.navigate().forward();
    }
    public void refresh(){
        driver.navigate().refresh();
    }
    private void makeFullScreen() {
        driver.manage().window().maximize();
    }


    public void openNewWindow(){
        executor = (JavascriptExecutor)driver;
        executor.executeScript("window.open()");
    }


}
