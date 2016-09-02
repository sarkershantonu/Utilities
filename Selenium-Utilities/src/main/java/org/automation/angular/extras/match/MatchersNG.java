package org.automation.angular.extras.match;

/**
 * Created by shantonu on 9/2/16.
 */
public class MatchersNG {
    public static org.hamcrest.Matcher<org.openqa.selenium. WebElementNG> attribute(
            java.lang.String attributeName, org.hamcrest.Matcher<java.lang.String> valueMatcher) {
        return org.openqa.selenium.lift.match.AttributeMatcher.attribute(attributeName, valueMatcher);
    }

    public static org.hamcrest.Matcher<java.lang.Integer> atLeast(int count) {
        return org.openqa.selenium.lift.match.NumericalMatchers.atLeast(count);
    }

    public static org.hamcrest.Matcher<java.lang.Integer> exactly(int count) {
        return org.openqa.selenium.lift.match.NumericalMatchers.exactly(count);
    }

    public static org.hamcrest.Matcher<org.openqa.selenium. WebElementNG> text(
            org.hamcrest.Matcher<java.lang.String> textMatcher) {
        return org.openqa.selenium.lift.match.TextMatcher.text(textMatcher);
    }

    public static org.hamcrest.Matcher<org.openqa.selenium. WebElementNG> selection() {
        return org.openqa.selenium.lift.match.SelectionMatcher.selection();
    }

    public static org.hamcrest.Matcher<org.openqa.selenium. WebElementNG> value(Object value) {
        return org.openqa.selenium.lift.match.ValueMatcher.value(value);
    }

    public static org.hamcrest.Matcher<org.openqa.selenium. WebElementNG> displayed() {
        return DisplayedMatcherNG.displayed();
    }
}
