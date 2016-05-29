package project.selenium.page.utils;

import automation.utils.UtilBase;
import com.google.common.base.Function;
import org.browser.manage.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shantonu on 4/10/16.
 */
public class ElementUtil extends UtilBase {
    private static String getText_JS = "return arguments[0].innerHTML";

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

                public Boolean apply(WebDriver driverObject) {
                    return isTextPresent(driverObject, by, text); //is the Text in the DOM
                }
            });
            isPresent = isTextPresent(super.driver, by, text);
            Browser.resetImplicitWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isPresent;
    }

    public WebElement waitForElementRefresh(final By by, int timeOutInSeconds) {
        WebElement element=null;
        try{
            Browser.nullifyImplicitWait();
            new WebDriverWait(super.driver, timeOutInSeconds) {}.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driverObject) {
                    driverObject.navigate().refresh(); //refresh the page ****************
                    return isElementPresentAndDisplay(driverObject, by);
                }});
            element = super.driver.findElement(by);
            Browser.resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> waitForListElementsPresent(final By by, int timeOutInSeconds) {
        List<WebElement> elements=null;
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until((new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driverObject) {
                    return areElementsPresent(driverObject, by);
                }
            }));

            elements = driver.findElements(by);
            Browser. resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    public WebElement waitForElementPresent(final By by, int timeOutInSeconds) {
        WebElement element=null;
        try{
            Browser. nullifyImplicitWait();

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            Browser. resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }
    public WebElement waitForElement(final By by, int timeOutInSeconds) {
        WebElement element = null;
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Browser. resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }
    public WebElement waitForElement(WebElement aElement, int timeOutInSeconds){
        WebDriverWait wait =  new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(aElement));
        return aElement;
    }

    public WebElement findElementAsyc(final By by , int timeSec){
        FluentWait wait = new FluentWait(driver).withTimeout(Browser.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS)
                .pollingEvery(timeSec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement found = (WebElement) wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver aDriver) {
                aDriver = driver;
                return aDriver.findElement(by);
            }
        });
        return found;
    }

    /**
     * FInding item async with 15s delay
     * @param by
     * @return
     */
    public WebElement findElementAsyc(final By by){
        return findElementAsyc(by,15);
    }
    public List<WebElement> findElementsAsyc(final By by){
        return findElementsAsyc(by,15);
    }
    public List<WebElement> findElementsAsyc(final By by , int timeSec){
        FluentWait wait = new FluentWait(driver).withTimeout(Browser.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS)
                .pollingEvery(timeSec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        List<WebElement> found = (List<WebElement>) wait.until(new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver aDriver) {
                aDriver = driver;
                return aDriver.findElements(by);
            }
        });
        return found;
    }

    /**
     * Alias function to wait for individual items with multiple selectors. This will be used to get group of items together
     * like Grid items. Wait will apply individual items. 
     * @param timeSec
     * @param by
     * @return
     */
    public List<WebElement> findElementsAsyc(int timeSec, final By... by ){
        List<WebElement> elements = new ArrayList();
        for(By b: by){
            FluentWait wait = new FluentWait(driver).withTimeout(Browser.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS)
                    .pollingEvery(timeSec, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            WebElement found = (WebElement) wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver aDriver) {
                    aDriver = driver;
                    return aDriver.findElement(b);
                }
            });
            elements.add(found);
        }
        return elements;
    }


    public String getTextByJS(WebElement element){
        executor = (JavascriptExecutor)driver;
        return (String)executor.executeScript(getText_JS, new Object[]{element});
    }

    public void waitForAjaxComplete(int sec){
        Browser.setWebDriverWait(sec).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver aDriver) {
                return new AjaxUtil(aDriver).isAjaxComplete();
            }
        });
    }

    public void isPageLoaded(int sec){
        Browser.setWebDriverWait(sec).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver aDriver) {
                return new AjaxUtil(aDriver).isPageLoaded();
            }
        });
    }


}
