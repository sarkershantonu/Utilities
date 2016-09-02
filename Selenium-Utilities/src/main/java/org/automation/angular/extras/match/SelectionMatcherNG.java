package org.automation.angular.extras.match;

import org.automation.angular.WebElementNG;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * Created by shantonu on 9/2/16.
 * todo
 */
public class SelectionMatcherNG extends TypeSafeMatcher<WebElementNG> {
    @Override
    protected boolean matchesSafely(WebElementNG item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }

    @Factory
    public static Matcher<WebElementNG> selection() {
        return new SelectionMatcherNG();
    }
}
