package org.automation.angular;


import org.automation.angular.scripts.Evaluate;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Angular Web Element.=> i change implementation
 */
//public final class NgWebElement implements WebElement, WrapsElement
// only thing added are the wait.. no more extra
public final class WebElementNG implements WebElement, WrapsElement, FindsByLinkText, FindsById, FindsByName, FindsByTagName, FindsByClassName, FindsByCssSelector, FindsByXPath, Locatable, HasIdentity,TakesScreenshot {

    private final transient NgSupport ngDriver;
    private final transient RemoteWebElement ngElement;
    public WebElementNG(final NgSupport drv, final WebElement elm) {
        this.ngDriver = drv;
        this.ngElement = (RemoteWebElement) elm;
    }

    @Override
    public WebElement getWrappedElement() {
        return this.ngElement;
    }

    @Override
    public void clear() {
        this.ngDriver.waitForAngular();
        this.ngElement.clear();
    }

    @Override
    public void click() {
        this.ngDriver.waitForAngular();
        this.ngElement.click();
    }

    public Object evaluate(final String expression) {
        this.ngDriver.waitForAngular();
        return ngDriver.executeScript( new Evaluate().content(), this.ngElement,expression);
    }

    @Override
    public WebElementNG findElement(By by) {
        if (by instanceof JavaScriptBy) {
            ((JavaScriptBy) by).root = this.ngElement;
        }
        this.ngDriver.waitForAngular();
        return new WebElementNG(this.ngDriver, this.ngElement.findElement(by));
    }

    public List<WebElementNG> findNgElements(By by) {
        final List<WebElement> temp = findElements(by);
        final List<WebElementNG> elements = new ArrayList<>();
        for (final WebElement element : temp) {
            elements.add(new WebElementNG(this.ngDriver, element));
        }
        return elements;
    }

    public List<WebElement> findElements(By by) {
        if (by instanceof JavaScriptBy) {
            ((JavaScriptBy) by).root = this.ngElement;
        }
        final List<WebElement> returnElements = this.ngElement.findElements(by);
        this.ngDriver.waitForAngular();
        return returnElements;
    }

    public String getAttribute(String arg0) {
        this.ngDriver.waitForAngular();
        return this.ngElement.getAttribute(arg0);
    }

    public String getCssValue(String arg0) {
        this.ngDriver.waitForAngular();
        return this.ngElement.getCssValue(arg0);
    }

    public Point getLocation() {
        this.ngDriver.waitForAngular();
        return this.ngElement.getLocation();
    }

    public Dimension getSize() {
        this.ngDriver.waitForAngular();
        return this.ngElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        this.ngDriver.waitForAngular();
        return ngElement.getRect();
    }


    public String getTagName() {
        this.ngDriver.waitForAngular();
        return this.ngElement.getTagName();
    }

    public String getText() {
        this.ngDriver.waitForAngular();
        return this.ngElement.getText();
    }

    public boolean isDisplayed() {
        this.ngDriver.waitForAngular();
        return this.ngElement.isDisplayed();
    }

    public boolean isEnabled() {
        this.ngDriver.waitForAngular();
        return this.ngElement.isEnabled();
    }

    public boolean isSelected() {
        this.ngDriver.waitForAngular();
        return this.ngElement.isSelected();
    }

    public void sendKeys(CharSequence... arg0) {
        this.ngDriver.waitForAngular();
        this.ngElement.sendKeys(arg0);

    }

    public void submit() {
        this.ngDriver.waitForAngular();
        this.ngElement.submit();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        this.ngDriver.waitForAngular();
        return this.ngElement.getScreenshotAs(target);
    }

    @Override
    public WebElement findElementByClassName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementById(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsById(using);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementByName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByName(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByTagName(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        this.ngDriver.waitForAngular();
        return this.ngElement.findElementsByXPath(using);
    }

    @Override
    public String getId() {
        this.ngDriver.waitForAngular();
        return this.ngElement.getId();
    }

    @Override
    public Coordinates getCoordinates() {
        return this.ngElement.getCoordinates();
    }




}
