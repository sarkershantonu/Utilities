package org.automation.angular.extras;

import org.automation.angular.WebDriverNG;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.lift.find.Finder;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.Collection;

import static org.openqa.selenium.lift.match.NumericalMatchers.atLeast;

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
        driverNG.quit();

    }

    @Override
    public void assertPresenceOf(Finder<WebElement, WebDriver> finder) {
        assertPresenceOf(atLeast(1), finder);
    }

    @Override
    public void assertPresenceOf(Matcher<Integer> cardinalityConstraint, Finder<WebElement, WebDriver> finder) {
        driverNG.waitForAngular();
        Collection<WebElement> foundElements = finder.findFrom(driverNG);

        if (!cardinalityConstraint.matches(foundElements.size())) {
            Description description = new StringDescription();
            description.appendText("\nExpected: ")
                    .appendDescriptionOf(cardinalityConstraint)
                    .appendText(" ")
                    .appendDescriptionOf(finder)
                    .appendText("\n     got: ")
                    .appendValue(foundElements.size())
                    .appendText(" ")
                    .appendDescriptionOf(finder)
                    .appendText("\n");

            failWith(description.toString());
        }
    }

    @Override
    public void type(String input, Finder<WebElement, WebDriver> finder) {

        driverNG.waitForAngular();
        WebElement element = findOneElementTo("type into", finder);
        element.sendKeys(input);
    }

    @Override
    public void clickOn(Finder<WebElement, WebDriver> finder) {
        driverNG.waitForAngular();
        WebElement element = findOneElementTo("click on", finder);
        element.click();
    }

    @Override
    public void waitFor(Finder<WebElement, WebDriver> finder, long timeout) {

    }

    @Override
    public void quit() {
        driverNG.quit();

    }

    private WebElement findOneElementTo(String action, Finder<WebElement, WebDriver> finder) {// todo finder for angular..
        driverNG.waitForAngular();
        Collection<WebElement> foundElements = finder.findFrom(driverNG);
        if (foundElements.isEmpty()) {
            failWith("could not find element to " + action);
        } else if (foundElements.size() > 1) {
            failWith("did not know what to " + action + " - ambiguous");
        }

        return foundElements.iterator().next();
    }

    private void failWith(String message) throws AssertionError {
        throw new java.lang.AssertionError(message);
    }
}
