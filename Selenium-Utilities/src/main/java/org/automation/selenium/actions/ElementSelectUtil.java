package org.automation.selenium.actions;

import org.automation.selenium.SeleniumUtilBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by shantonu on 8/24/16.
 */
public class ElementSelectUtil extends SeleniumUtilBase {


    public ElementSelectUtil(WebDriver aDriver) {
        super(aDriver);
    }

    public void selectSubItemBy(WebElement element, String name){
        new Select(element).selectByVisibleText(name);
    }
    public void selectSubItemByValue(WebElement element, String value){
        new Select(element).selectByValue(value);
    }
    public void selectSubItemBy(WebElement element, int index){
        new Select(element).selectByIndex(index);
    }
}
