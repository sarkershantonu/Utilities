package project.selenium.page.utils;

import automation.entity.TestError;
import automation.utils.ExceptionManager;
import automation.utils.UtilBase;
import org.apache.commons.io.IOUtils;
import org.browser.manage.Browser;
import org.browser.utils.PageSourceUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.InputStream;
import java.io.IOException;

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
    public void RunJavaScript(String js){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript(js);
    }

    public String readJsLibrary(String jsFileName){
        InputStream input  = new PageSourceUtil(driver).getResourceAsStream(jsFileName);
        String html2CanvasLib = "" ;
        try {
                html2CanvasLib = IOUtils.toString(input);
            } catch (IOException e) {
                log.info("Error Loading : "+ jsFileName + "\n"+e.getMessage());
                ExceptionManager.performDefaultAction("Error Loading : "+ jsFileName , e, new TestError());

            }finally {
                IOUtils.closeQuietly(input);
            }
        return html2CanvasLib;
    }
    public JavascriptExecutor getJsExecutor(){
        return (JavascriptExecutor)driver;

    }
}
