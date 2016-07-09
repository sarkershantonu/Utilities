package org.automation.selenium.elements;


import org.automation.selenium.UtilBase;
import org.automation.selenium.browser.Browser;
import org.automation.selenium.javascripts.AjaxUtil;
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
    public boolean isTextPresent(By by, String text)
    {
        try {
            return driver.findElement(by).getText().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element){

        return element.isDisplayed();
    }

    public boolean isElementPresent(By by) {
        try {
            return driver.findElement(by)!=null;//if it does not find the element throw NoSuchElementException, which calls "catch(Exception)" and returns false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areElementsPresent( By by) {
        try {
            return driver.findElements(by)!=null;

        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementPresentAndDisplay( By by) {
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

                public Boolean apply(WebDriver aDriver) {
                    return isTextPresent(by, text); //is the Text in the DOM
                }
            });
            isPresent = isTextPresent(by, text);
            Browser.resetImplicitWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isPresent;
    }

    /**
     * wait for elements and if noxception on visibility, sents true.
     * @param element
     * @param sec
     * @return
     */
    public boolean waitForElementVisibility(WebElement element, int sec){
        WebDriverWait wait = Browser.setWebDriverWait(sec);
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean waitForElementsVisibility(List<WebElement> elements, int sec){
        WebDriverWait wait = Browser.setWebDriverWait(sec);
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void pauseUntillElementInVisible(WebElement element){
        while (waitForElementVisibility(element,5)){
            Browser.pause(2);
        }
    }

    public WebElement getTableRowByLabel(String label) {
        return driver.findElement(By.xpath(".//*[contains(text(), \"" + label + "\")]" + "//ancestor::tr"));
    }
    public WebElement waitForElementRefresh(final By by, int timeOutInSeconds) {
        WebElement element=null;
        try{
            Browser.nullifyImplicitWait();
            new WebDriverWait(driver, timeOutInSeconds) {}.
                    until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver aDriver) {
                    aDriver = driver;
                    aDriver.navigate().refresh(); //refresh the page ****************
                    return isElementPresentAndDisplay(by);
                }});
            element = driver.findElement(by);
            Browser.resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }


    public List<WebElement> waitForElements(final By by, int timeOutInSeconds) {
        List<WebElement> elements=null;
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until((new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver aDriver) {

                    return areElementsPresent(by);
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
            Browser.nullifyImplicitWait();

            WebDriverWait wait =Browser.setWebDriverWait(timeOutInSeconds);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            Browser. resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> waitForElementsPresent(int timeOutInSeconds, final By by) {
        List<WebElement> elements=new ArrayList();
        try{
            Browser. nullifyImplicitWait();
            WebDriverWait wait =Browser.setWebDriverWait(timeOutInSeconds);
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            Browser. resetImplicitWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
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
    public List<WebElement> findElementsAsyc(final By... by){
        return findElementsAsyc(15,by);
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

    public boolean checkAttribute(String expectedValueOfAttribute, WebElement element, String attribute ){

        String result =  element.getAttribute(attribute);

        if(result.equals(attribute))// if attribute is not present, this gives the value of the property of attribute.
            return false;
        if(result==null|| result.equals("null"))
            return false;//value is not set of the attribute
        return result.equals(expectedValueOfAttribute);

    }


}
