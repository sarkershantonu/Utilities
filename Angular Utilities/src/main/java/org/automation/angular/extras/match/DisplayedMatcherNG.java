package org.automation.angular.extras.match;

import org.automation.angular.WebElementNG;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Created by shantonu on 9/2/16.
 */
public class DisplayedMatcherNG extends TypeSafeMatcher<WebElementNG> {
    @Override
    protected boolean matchesSafely(WebElementNG item) {
        return item.isDisplayed();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("displayed");
    }

    @Factory
    public static Matcher<WebElementNG> displayed() {
        return new DisplayedMatcherNG();
    }
}
