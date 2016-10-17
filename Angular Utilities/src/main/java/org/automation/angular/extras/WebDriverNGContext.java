package org.automation.angular.extras;

import org.automation.angular.WebDriverNG;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.lift.find.Finder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

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
    public void clickOnFirst(Finder<WebElement, WebDriver> finder) {

        WebElement element = findFirstElementTo("click on", finder);
        element.click();
    }

    @Override
    public void clickOn(Finder<WebElement, WebDriver> finder) {
        WebElement element = findOneElementTo("click on", finder);
        element.click();
    }

    @Override
    public void waitFor(Finder<WebElement, WebDriver> finder, long timeMS) {
        driverNG.waitForAngular();
        final ExpectedCondition<Boolean> elementsDisplayedPredicate = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                driverNG.waitForAngular();
                final Collection<WebElement> elements = finder.findFrom(driver);
                for (WebElement webElement : elements) {
                    if (webElement.isDisplayed()) {
                        return true;
                    }
                }
                return false;
            }
        };

        final long defaultSleepTimeoutMillis = FluentWait.FIVE_HUNDRED_MILLIS.in(TimeUnit.MILLISECONDS);
        final long sleepTimeout = (timeMS > defaultSleepTimeoutMillis)
                ? defaultSleepTimeoutMillis : timeMS / 2;

        Wait<WebDriver> wait = new WebDriverWait(driverNG, clock, sleeper, millisToSeconds(timeMS),
                sleepTimeout) {
            @Override
            public RuntimeException timeoutException(String message, Throwable lastException) {
                throw new AssertionError("Element was not rendered within " + timeMS + "ms");
            }
        };
        wait.until(elementsDisplayedPredicate);

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
        throw new AssertionError(message);
    }

    private static long millisToSeconds(final long timeoutMillis) {
        return ceiling(((double) timeoutMillis) / 1000);
    }

    private static long ceiling(final double value) {
        final long asLong = (long) value;
        final int additional = value - asLong > 0 ? 1 : 0;
        return asLong + additional;
    }
    private WebElement findFirstElementTo(String action, Finder<WebElement, WebDriver> finder) {
        driverNG.waitForAngular();
        Collection<WebElement> foundElements = finder.findFrom(driverNG);
        if (foundElements.isEmpty()) {
            failWith("could not find element to " + action);
        }

        return foundElements.iterator().next();
    }
}
