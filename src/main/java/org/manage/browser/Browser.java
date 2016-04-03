package org.manage.browser;

import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/2/16.
 */
public class Browser {

    private static WebDriver driver = null;

    public static WebDriver getABrowser(){
        if(driver==null){
            driver = new BrowserFactory().createBrowser();
        }
        return driver;
    }

    private Browser(){}

}
