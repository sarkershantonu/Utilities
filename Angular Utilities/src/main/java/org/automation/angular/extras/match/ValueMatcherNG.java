package org.automation.angular.extras.match;

import org.automation.angular.WebElementNG;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by shantonu on 9/2/16.
 * todo
 */
public class ValueMatcherNG extends TypeSafeMatcher<WebElementNG> {

    private final Object value;

    public ValueMatcherNG(Object value) {
        this.value = value;
    }

    @Override
    public boolean matchesSafely(WebElementNG item) {
        return item.getAttribute("value").equals(value);
    }

    public void describeTo(Description description) {
        description.appendText("should have value ").appendValue(value);
    }

    @Factory
    public static Matcher<WebElementNG> value(final Object value) {
        return new ValueMatcherNG(value);
    }


}
