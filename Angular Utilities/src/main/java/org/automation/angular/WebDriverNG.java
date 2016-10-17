package org.automation.angular;


import org.automation.angular.scripts.WaitForAngular;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Angular WebDriver Implementation.
 * Only difference is adding wait for angular JS to run, no extra
 *
 * TODO =>  Shantonu, adding all javascript capability to enrich this driver... example form ajax& js examples
 */
public final class WebDriverNG implements WebDriver, NgSupport{
    private final WebDriver driver;
    private final JavascriptExecutor jsExecutor;
    private final String root;
    private NgModule[] mockModules;
    public boolean IgnoreSynchronization;
    public int MaxIterations;


    @Override
    public JavascriptExecutor getJsExecutor(){
        return jsExecutor;
    }
    public WebDriverNG(WebDriver driver)
    {
        this(driver,false);//default behavior = ignore sync is false

    }
    public WebDriverNG(WebDriver driver, boolean ignoreSync)
    {
        this(driver, "body",ignoreSync);
    }
    public WebDriverNG(final WebDriver drv, final String rootElement, boolean ignoreSync) {
        if (!(drv instanceof JavascriptExecutor)) {throw new WebDriverException("Browser must be capable to run javascript");
        }
        this.driver = drv;
        this.jsExecutor = (JavascriptExecutor) drv;
        this.root = rootElement;
        this.IgnoreSynchronization = ignoreSync;
    }

    public WebDriverNG(WebDriver driver, NgModule[] mockModules)
    {
        this(driver);
        this.mockModules = mockModules;
    }
    public WebDriverNG(WebDriver driver, boolean ignoreSync, String rootElement , NgModule[] mockModules)
    {
        this(driver,rootElement,ignoreSync);
        this.mockModules = mockModules;
    }

    @Override
    public void waitForAngular() {
        if(this.IgnoreSynchronization){
            this.jsExecutor.executeScript(new WaitForAngular().content(),this.root);
        }else {
            this.jsExecutor.executeAsyncScript(new WaitForAngular().content(),this.root);
        }
    }


    public List<WebElementNG> findNGElements(By arg0)
    {
        this.waitForAngular();
        List<WebElement> temp = this.findElements(arg0);

        List<WebElementNG> returnElements = new ArrayList<WebElementNG>();
        for(WebElement currrentEle : temp)
        {
            returnElements.add(new WebElementNG(this, currrentEle));
        }
        return returnElements;
    }

    public WebElementNG findNGElement(final By by) {
        this.waitForAngular();
        return new WebElementNG(this, this.driver.findElement(by));
    }

    @Override
    public List<WebElement> findElements(final By by) {
        this.waitForAngular();
        return this.driver.findElements(by);
    }

    @Override
    public WebElement findElement(final By by) {
        this.waitForAngular();
        return this.driver.findElement(by);
    }

    @Override
    public void get(final String arg0) {
        this.waitForAngular();
        this.driver.get(arg0);
    }

    @Override
    public WebDriver getWrappedDriver() {
        return this.driver;
    }
    @Override
    public String getCurrentUrl() {
        this.waitForAngular();
        return this.driver.getCurrentUrl();
    }

    @Override
    public String getPageSource() {
        this.waitForAngular();
        return this.driver.getPageSource();
    }

    @Override
    public String getTitle() {
        this.waitForAngular();
        return this.driver.getTitle();
    }

    @Override
    public String getWindowHandle() {
        this.waitForAngular();
        return this.driver.getWindowHandle();
    }

    @Override
    public Set<String> getWindowHandles() {
        this.waitForAngular();
        return this.driver.getWindowHandles();
    }

    @Override
    public Options manage() {
        return this.driver.manage();
    }

    @Override
    public Navigation navigate() {
        return new NgNavigation(this.driver.navigate());
    }

    @Override
    public void quit() {
        this.driver.quit();

    }

    @Override
    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }
    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        waitForAngular();
        return jsExecutor.executeScript(script,args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        waitForAngular();
        return jsExecutor.executeAsyncScript(script, args);
    }
}
