package org.automation.angular.extras.match;

import org.automation.angular.WebElementNG;
import org.openqa.selenium.lift.match.NumericalMatchers;

/**
 * Created by shantonu on 9/2/16.
 */
public class MatchersNG {
    public static org.hamcrest.Matcher<WebElementNG> attribute(
            String attributeName, org.hamcrest.Matcher<String> valueMatcher) {
        return AttributeMatcherNG.attribute(attributeName, valueMatcher);
    }

    public static org.hamcrest.Matcher<Integer> atLeast(int count) {
        return NumericalMatchers.atLeast(count);
    }

    public static org.hamcrest.Matcher<Integer> exactly(int count) {
        return NumericalMatchers.exactly(count);
    }

    public static org.hamcrest.Matcher<WebElementNG> text(
            org.hamcrest.Matcher<String> textMatcher) {
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
