package org.browser.manage;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by shantonu on 4/2/16.
 * Maintain Singleton behavior for driver object
 * Responsibilities
 * => get browser but singletone way
 * => get JS executor
 * Note : we are not maintaining browser instance logic here. That will be maintain by Browser factory.
 */
public class Browser {

    //todo => need to read from property
    public static final long DEFAULT_WAIT_4_PAGE = 30;
    public static final long DEFAULT_WAIT_4_ELEMENT = 10;


    private static WebDriver driver = null;

    public static WebDriver getInstance(){
        if(driver==null){
            driver = new BrowserFactory().getABrowser();
            initiDriver();
        }
        return driver;
    }

    private Browser(){}

    public static void close(){

        try {
            driver.close();
        }catch (Exception e){
            driver.quit();
        }
    }

    public static void closeAllChild(String parentHandle){
        Set<String> windows =   driver.getWindowHandles();
        windows.remove(parentHandle);
        for(String s : windows){
            driver.switchTo().window(s);
            close();
        }
    }
    /**
     * Size of screen
     * Timeout
     * All waiting
     */
    private static void initiDriver(){
        setImplicitWait(DEFAULT_WAIT_4_PAGE);
        setJSTimeOut(5);
        new WebDriverWait(driver,DEFAULT_WAIT_4_ELEMENT);
    }
    public static void resetImplicitWait(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public static void setImplicitWait(long newWaittime_InSeconds) {
        nullifyImplicitWait();
        driver.manage().timeouts().implicitlyWait(newWaittime_InSeconds, TimeUnit.SECONDS);
    }
    public static void nullifyImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static JavascriptExecutor getJSexcutor(){
        return (JavascriptExecutor)driver;
    }
    public static void setJSTimeOut(long sec){
        driver.manage().timeouts().setScriptTimeout(sec,TimeUnit.SECONDS);
    }
    public static WebDriverWait setWebDriverWait(long sec){
        WebDriverWait wait ;
        if(DEFAULT_WAIT_4_ELEMENT<sec){
            wait =  new WebDriverWait(driver, sec);
        }else
        {
            wait = new WebDriverWait(driver,DEFAULT_WAIT_4_ELEMENT);
        }
        return wait;
    }

}
