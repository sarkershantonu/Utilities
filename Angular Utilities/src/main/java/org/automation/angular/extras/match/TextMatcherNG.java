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
public class TextMatcherNG extends TypeSafeMatcher<WebElementNG> {

    private final Matcher<String> matcher;

    TextMatcherNG(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WebElementNG item) {
        return matcher.matches(item.getText());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("text ");
        matcher.describeTo(description);
    }

    @Factory
    public static Matcher<WebElementNG> text(final Matcher<String> textMatcher) {
        return new TextMatcherNG(textMatcher);
    }
}
