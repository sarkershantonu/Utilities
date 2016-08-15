package org.automation.selenium.javascripts;


import org.automation.selenium.SeleniumUtilBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 4/14/16.
 * todo => this is not a proper class, this will devided to other classes based on responsibility types.
 * this is initial, so , will be fixed in releases.
 */
public class AjaxSeleniumUtil extends SeleniumUtilBase {

    private static String ajaxCompletion_JS="return $.active == 0";
    private static String scrollDown_JS="arguments[0].scrollIntoView(true);";
    private static String rowExpand_JS="arguments[0].setAttribute('class', 'open');";
    private static String isLoad_JS="return document.readyState";
    private static String formUpload_JS="document.getElementById('uploadNonDocStoreDocumentForm').submit()";
    private static String fileUpload_JS="document.getElementsByName('uploadedDocument')[0].click()";
    private static String confirmAlert_JS="function() { browser.alert.ok }";
    public AjaxSeleniumUtil(WebDriver aDriver) {
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

    public void waitByJS(int sec){
        executor.executeAsyncScript("window.setTimeout(arguments[0])", new Object[]{sec});
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


    public void click(WebElement element){
        executor.executeScript("arguments[0].click();", element);
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

    public void confirmAlert(){
        executor.executeScript(confirmAlert_JS, new Object[0]);
    }

    public boolean isHorizScrollPresent(WebElement element){
        long clientWidth = ((Long)executor.executeScript("return arguments[0].clientWidth", new Object[]{element})).longValue();
        long scrollWidth = ((Long)executor.executeScript("return arguments[0].scrollWidth", new Object[]{element})).longValue();
        return scrollWidth>clientWidth;
    }


    /**
     * TODO => currently it is getting only 'span.ui-button-text', based on page behavior, we should make it generic
     * Adding new function to day on a button click.. all by JS
     * @param elementAttribute
     * @param sec
     */
    public void clickButtonWithDelayByJS(int sec, String elementAttribute ){

        String script = String.format(
                "setInterval(function(){ "
                        +"var btn = document.querySelector(\'#%s\'); "
                        +"btn && btn.click(); "
                +"setInterval(function(){"
                        +"var btn = document.querySelector(\'span.ui-button-text\');"
                        +"btn && btn.click();"
                        +"},"
                        +" 5*1000);"
                        +"}, %s*1000);",
                new Object[]{elementAttribute, Integer.toString(sec)});
        executor.executeScript(script, new Object[0]);
    }

    /**
     * inject JS in page : Fully experimental part.
     *
     */

    public void addJS_TO_Current_Page(String jsFileName, String pageName){
        String jsFullPath = System.getProperty("user.dir")+"src/main/resources/js/"+jsFileName+".js";
        String script = "var oHead = document.getElementsByTagName(\'HEAD\').item(0)var oScript = document.createElement(\"script\");oScript.type = \"text/javascript\";oScript.src = "
                + jsFullPath + "oHead.appendChild(oScript);";
        executor.executeScript(script, new Object[0]);
    }

    /**
     *
     * @param newJSFunction = functions should be plain and simple statement.
     * @param elementIdentifier : first one tag, 2nd one item array position indesx (zero based)
     * @param event
     * Example = "document.getElementsByTagName(\"input\").item(2).click = new function(){window.location=\"http://google.com\"};";
     * identifiers  => input,2.
     */
    public void addJsToItemAsync(String event, String newJSFunction, String... elementIdentifier ){
        String script = "document.getElementsByTagName(\""+elementIdentifier[0]+"\").item("+elementIdentifier[1]+")."+event+"="+"new function(){"+newJSFunction+"};";
        executor.executeAsyncScript(script);
    }
}
