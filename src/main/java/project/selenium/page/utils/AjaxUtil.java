package project.selenium.page.utils;

import automation.utils.UtilBase;
import org.browser.manage.Browser;
import org.omg.CORBA.Object;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/14/16.
 */
public class AjaxUtil extends UtilBase {

    private static String ajaxCompletionJS="return $.active == 0";
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    public AjaxUtil(WebDriver aDriver) {
        super(aDriver);
    }

    public void waitForAjax(final By by,int timeout){
        JavascriptExecutor executor = (JavascriptExecutor)driver;

    }
    public void runAjax(String ajaxString){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeAsyncScript(ajaxString);
    }

    /**
     * this will check if ajax is completed to run or not
     */
    public boolean isAjaxComplete(){
        return ((Boolean) executor.executeScript(ajaxCompletionJS, new Object[0])).booleanValue();
    }


}
