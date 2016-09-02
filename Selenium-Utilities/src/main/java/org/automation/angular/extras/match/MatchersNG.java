package org.automation.angular.extras.match;

import org.automation.angular.WebElementNG;
import org.openqa.selenium.lift.match.NumericalMatchers;

/**
 * Created by shantonu on 9/2/16.
 */
public class MatchersNG {
    public static org.hamcrest.Matcher<WebElementNG> attribute(
            java.lang.String attributeName, org.hamcrest.Matcher<java.lang.String> valueMatcher) {
        return AttributeMatcherNG.attribute(attributeName, valueMatcher);
    }

    public static org.hamcrest.Matcher<java.lang.Integer> atLeast(int count) {
        return NumericalMatchers.atLeast(count);
    }

    public static org.hamcrest.Matcher<java.lang.Integer> exactly(int count) {
        return NumericalMatchers.exactly(count);
    }

    public static org.hamcrest.Matcher<WebElementNG> text(
            org.hamcrest.Matcher<java.lang.String> textMatcher) {
        return TextMatcherNG.text(textMatcher);
    }

    public static org.hamcrest.Matcher< WebElementNG> selection() {
        return SelectionMatcherNG.selection();
    }

    public static org.hamcrest.Matcher< WebElementNG> value(Object value) {
        return ValueMatcherNG.value(value);
    }

    public static org.hamcrest.Matcher< WebElementNG> displayed() {
        return DisplayedMatcherNG.displayed();
    }
}
