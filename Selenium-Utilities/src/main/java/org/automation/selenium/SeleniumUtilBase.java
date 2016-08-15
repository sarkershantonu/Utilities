package org.automation.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by shantonu on 4/10/16.
 * This is mother of all utils
 * Must wired with driver
 * Static method only contains global items, except that all are actually object items.
 * All utils keeping this as base are contextualized on page.
 */
public abstract class SeleniumUtilBase {
    protected Logger log = null;
    protected WebDriver driver = null;
    protected JavascriptExecutor executor ;

    public SeleniumUtilBase(WebDriver aDriver){
        this.driver = aDriver;
        log = LoggerFactory.getLogger(this.getClass());
    }
}
