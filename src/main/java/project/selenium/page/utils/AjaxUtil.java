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
    private static String rowExpand_JS="arguments[0].setAttribute('class', 'open');";
    private static String isLoad_JS="return document.readyState";
    private static String formUpload_JS="document.getElementById('uploadNonDocStoreDocumentForm').submit()";
    private static String fileUpload_JS="document.getElementsByName('uploadedDocument')[0].click()";

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
     * Page load checking
     * @return
     */
    public boolean isPageLoaded(){
        return  (Boolean) executor.executeScript(isLoad_JS, new Object[0].equals("complete"));
    }

    /**
     * For the page which will have this
     */
    public void clickUpload(){
        executor.executeScript(formUpload_JS, new Object[0]);
    }

    public void clickFileUpload(){
        executor.executeScript(fileUpload_JS, new Object[0]);
    }


/**
 * Mouse Movements ******************************************************
 */
    public void scrollDown(WebElement element){
        executor.executeScript(scrollDown_JS, new Object[]{element});
    }


/**
 * Expand table
  */

    public void expandTableContains(WebElement row){
        executor.executeScript(rowExpand_JS, new Object[]{row});
    }
}
