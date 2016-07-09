package org.automation.utils;

import automation.utils.UtilBase;
import org.openqa.selenium.WebDriver;
import project.selenium.PageBase;

/**
 * Created by shantonu on 4/14/16.
 * Providing utils for page context
 */
public class PageUtil extends UtilBase {
    public PageUtil(WebDriver aDriver) {
        super(aDriver);
    }

    public void waitForPageLoad(PageBase aPage){
        //todo , this can be ajax based page load check or JS base page load check
    }public void waitForPageLoad(){
        //todo , this can be ajax based page load check or JS base page load check => generic for all pages
    }
    public boolean isPageLoaded(){

        return false; //todo
    }
}
