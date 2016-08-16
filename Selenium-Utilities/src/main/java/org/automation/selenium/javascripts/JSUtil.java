package org.automation.selenium.javascripts;


import org.apache.commons.io.IOUtils;
import org.automation.selenium.SeleniumUtilBase;
import org.automation.selenium.browser.Browser;

import org.automation.selenium.page.PageSourceUtil;

import org.automation.utils.tracking.error.ExceptionManager;
import org.automation.utils.tracking.error.TestError;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shantonu on 4/10/16.
 */
public class JSUtil extends SeleniumUtilBase {

    public static String jqueryProcessString = "return jQuery.active == 0";

    public JSUtil(WebDriver aDriver) {
        super(aDriver);
    }
    public boolean waitForJavaScriptCondition(final String javaScript, int timeOutInSeconds) {
        boolean jscondition = false;
        try{
            Browser.nullifyImplicitWait();
            new WebDriverWait(super.driver, timeOutInSeconds) {
            }.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript(javaScript);
                }
            });
            jscondition =  (Boolean) executor.executeScript(javaScript);
            Browser.resetImplicitWait();
            return jscondition;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public  boolean waitForJQueryProcessing(int timeOutInSeconds){
        return waitForJavaScriptCondition(jqueryProcessString,timeOutInSeconds);
    }
    public void runJSAsyc(String js){
        executor.executeAsyncScript(js);
    }
    public void runJSSyc(String js){
        executor.executeScript(js);
    }

}
