package org.automation.angular.extras;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.lift.find.Finder;

/**
 * Created by shantonu on 9/1/16.
 */
public class WebDriverNGContext implements TestContext {
    @Override
    public void goTo(String url) {

    }

    @Override
    public void assertPresenceOf(Finder<WebElement, WebDriver> finder) {

    }

    @Override
    public void assertPresenceOf(Matcher<Integer> cardinalityConstraint, Finder<WebElement, WebDriver> finder) {

    }

    @Override
    public void type(String input, Finder<WebElement, WebDriver> finder) {

    }

    @Override
    public void clickOn(Finder<WebElement, WebDriver> finder) {

    }

    @Override
    public void waitFor(Finder<WebElement, WebDriver> finder, long timeout) {

    }

    @Override
    public void quit() {

    }
}
