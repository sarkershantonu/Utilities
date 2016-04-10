package project.selenium.page.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/10/16.
 */
public class ElementUtil extends UtilBase{
    public ElementUtil(WebDriver aDriver) {
        super(aDriver);
    }
    public boolean isTextPresent(WebDriver driver, By by, String text)
    {
        try {
            return driver.findElement(by).getText().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }
    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);//if it does not find the element throw NoSuchElementException, which calls "catch(Exception)" and returns false;
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean areElementsPresent(WebDriver driver, By by) {
        try {
            driver.findElements(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private boolean isElementPresentAndDisplay(WebDriver driver, By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
