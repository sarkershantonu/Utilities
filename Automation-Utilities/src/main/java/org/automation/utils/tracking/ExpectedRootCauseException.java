
import org.hamcrest.Matcher;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.ubs.bookmaster.ng.test.matchers.ThrowableRootCauseMessageMatcher.hasRootCauseMessage;
import static com.ubs.bookmaster.ng.test.matchers.ThrowableRootCauseMatcher.hasRootCause;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

public class ExpectedRootCauseException implements TestRule {
    private final ExpectedException delegate = ExpectedException.none();

    public static ExpectedRootCauseException none() {
        return new ExpectedRootCauseException();
    }

    private ExpectedRootCauseException() {
    }

    public ExpectedException reportMissingExceptionWithMessage(String message) {
        return delegate.reportMissingExceptionWithMessage(message);
    }

    public void expect(Matcher<?> matcher) {
        delegate.expect(matcher);
    }

    public void expect(Class<? extends Throwable> type) {
        delegate.expect(type);
    }

    public void expectMessage(String substring) {
        delegate.expectMessage(substring);
    }

    public void expectMessage(Matcher<String> matcher) {
        delegate.expectMessage(matcher);
    }

    public void expectRootCauseMessage(String message) {
        expectRootCauseMessage(equalTo(message));
    }

    public void expectRootCauseMessageContains(String message) {
        expectRootCauseMessage(containsString(message));
    }

    public void expectRootCauseMessage(Matcher<String> matcher) {
        expect(hasRootCauseMessage(matcher));
    }

    public void expectRootCause(Matcher<? extends Throwable> matcher) {
        expect(hasRootCause(matcher));
    }

    public void expectCause(Matcher<? extends Throwable> expectedCause) {
        delegate.expectCause(expectedCause);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return delegate.apply(base, description);
    }

}
