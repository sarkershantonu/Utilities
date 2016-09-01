package org.automation.angular.extras;

import org.automation.angular.WebDriverNG;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.lift.find.Finder;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;

/**
 * Created by shantonu on 9/1/16.
 * todo
 */
public class WebDriverNGContext implements TestContext {
    private WebDriverNG driverNG;
    private final Clock clock;
    private final Sleeper sleeper;

    WebDriverNGContext(WebDriverNG driver, Clock clock, Sleeper sleeper) {
        this.driverNG = driver;
        this.clock = clock;
        this.sleeper = sleeper;
    }

    public WebDriverNGContext(WebDriverNG driver) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER);
    }

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
