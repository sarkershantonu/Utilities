package project.selenium.page.utils;

import org.browser.manage.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by shantonu on 4/10/16.
 */
public class JavaScriptUtil extends UtilBase {

    public static String jqueryProcessString = "return jQuery.active == 0";

    public JavaScriptUtil(WebDriver aDriver) {
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
            jscondition =  (Boolean) ((JavascriptExecutor) super.driver).executeScript(javaScript);
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
}
