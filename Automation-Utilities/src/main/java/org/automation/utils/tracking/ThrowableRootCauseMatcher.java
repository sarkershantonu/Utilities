package org.automation.utils.tracking;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.google.common.base.Throwables;

/**
 * A matcher that applies a delegate matcher to the root cause of the current Throwable, returning the result of that match.
 *
 * @param <T> the type of the throwable being matched
 */
public class ThrowableRootCauseMatcher<T extends Throwable> extends TypeSafeMatcher<T> {

    private final Matcher<? extends Throwable> matcher;

    public ThrowableRootCauseMatcher(Matcher<? extends Throwable> rootCauseMatcher) {
        this.matcher = rootCauseMatcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("exception with cause ");
        description.appendDescriptionOf(matcher);
    }

    @Override
    protected boolean matchesSafely(T item) {
        return matcher.matches(Throwables.getRootCause(item));
    }

    @Override
    protected void describeMismatchSafely(T item, Description description) {
        description.appendText("root cause ");
        matcher.describeMismatch(Throwables.getRootCause(item), description);
    }

    /**
     * Returns a matcher that verifies that the outer exception has a root cause for which the supplied matcher evaluates to true.
     *
     * @param matcher to apply to the root cause of the outer exception
     * @param <T> type of the outer exception
     * @return - matcher
     */
    @Factory
    public static <T extends Throwable> Matcher<T> hasRootCause(final Matcher<? extends Throwable> matcher) {
        return new ThrowableRootCauseMatcher<T>(matcher);
    }
}
