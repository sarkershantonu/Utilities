package org.automation.utils.testing;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.google.common.base.Throwables;

public class ThrowableRootCauseMessageMatcher<T extends Throwable> extends TypeSafeMatcher<T> {

    private final Matcher<String> matcher;

    public ThrowableRootCauseMessageMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("exception with message ");
        description.appendDescriptionOf(matcher);
    }

    @Override
    protected boolean matchesSafely(T item) {
        final String message = extractRootCauseMessage(item);
        return matcher.matches(message);
    }

    @Override
    protected void describeMismatchSafely(T item, Description description) {
        description.appendText("message ");
        final String message = extractRootCauseMessage(item);
        matcher.describeMismatch(message, description);
    }

    private String extractRootCauseMessage(T item) {
        final Throwable rootCause = Throwables.getRootCause(item);
        final String message = rootCause.getMessage();
        return message;
    }

    @Factory
    public static <T extends Throwable> Matcher<T> hasRootCauseMessage(final Matcher<String> matcher) {
        return new ThrowableRootCauseMessageMatcher<T>(matcher);
    }
}
