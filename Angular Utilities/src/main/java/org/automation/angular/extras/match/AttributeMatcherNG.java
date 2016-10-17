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
public class AttributeMatcherNG extends TypeSafeMatcher<WebElementNG> {

    private final Matcher<String> matcher;
    private final String name;

    AttributeMatcherNG(String name, Matcher<String> matcher) {
        this.name = name;
        this.matcher = matcher;
    }
    @Override
    public boolean matchesSafely(WebElementNG item) {
        return matcher.matches(item.getAttribute(name));
    }

    public void describeTo(Description description) {
        description.appendText("attribute ").appendValue(name);
        matcher.describeTo(description);
    }

    @Factory
    public static Matcher<WebElementNG> attribute(final String name, final Matcher<String> valueMatcher) {
        return new AttributeMatcherNG(name, valueMatcher);
    }

}
