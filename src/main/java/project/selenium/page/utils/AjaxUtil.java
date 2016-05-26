package project.selenium.page.utils;

import automation.utils.UtilBase;
import org.browser.manage.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 4/14/16.
 */
public class AjaxUtil extends UtilBase {

    private static String ajaxCompletion_JS="return $.active == 0";
    private static String scrollDown_JS="arguments[0].scrollIntoView(true);";
    JavascriptExecutor executor ;
    public AjaxUtil(WebDriver aDriver) {
        super(aDriver);
        executor =(JavascriptExecutor)driver;
    }

    public void waitForAjax(final By by,int timeout){


    }
    public void runAjax(String ajaxString){
        executor.executeAsyncScript(ajaxString);
    }

    /**
     * this will check if ajax is completed to run or not
     */
    public boolean isAjaxComplete(){
        return ((Boolean) executor.executeScript(ajaxCompletion_JS, new Object[0])).booleanValue();
    }

/**
 * Mouse Movements ******************************************************
 */
    public void scrollDown(WebElement element){
        executor.executeScript(scrollDown_JS, new Object[]{element});
    }

}
