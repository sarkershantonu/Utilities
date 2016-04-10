package project.selenium.page.utils;

import org.browser.manage.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    public boolean isElementPresentAndDisplay(WebDriver driver, By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean waitForTextPresent(final By by, final String text, int timeOutInSeconds) {
        boolean isPresent = false;
        try{
            Browser.nullifyImplicitWait();
            new WebDriverWait(super.driver, timeOutInSeconds) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return isTextPresent(driverObject, by, text); //is the Text in the DOM
                }
            });
            isPresent = isTextPresent(super.driver, by, text);
            Browser.resetImplicitWait();
            return isPresent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public WebElement waitForElementRefresh(final By by, int timeOutInSeconds) {
        WebElement element;
        try{
            Browser.nullifyImplicitWait();
            new WebDriverWait(super.driver, timeOutInSeconds) {}.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    driverObject.navigate().refresh(); //refresh the page ****************
                    return isElementPresentAndDisplay(driverObject, by);
                }});
            element = super.driver.findElement(by);
            Browser.resetImplicitWait();
            return element; //return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WebElement> waitForListElementsPresent(final By by, int timeOutInSeconds) {
        List<WebElement> elements;
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until((new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    return areElementsPresent(driverObject, by);
                }
            }));

            elements = driver.findElements(by);
            Browser. resetImplicitWait();
            return elements; //return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public WebElement waitForElementPresent(final By by, int timeOutInSeconds) {
        WebElement element;
        try{
            Browser. nullifyImplicitWait();

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            Browser. resetImplicitWait();
            return element; //return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public WebElement waitForElement(final By by, int timeOutInSeconds) {
        WebElement element;
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Browser. resetImplicitWait();
            return element; //return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
