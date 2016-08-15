package org.automation.angular.scripts.io;

public final class JsLoadException extends RuntimeException {

    public static final String MESSAGE =
        "Failed to get script contents for file %s";


    public JsLoadException(final Throwable cause, final String filename) {
        super(String.format(JsLoadException.MESSAGE, filename), cause);
    }

    public JsLoadException(final String filename) {
        super(String.format(JsLoadException.MESSAGE, filename));
    }
}
