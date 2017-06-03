package org.automation.http.exception;

/**
 * Created by shantonu on 6/3/17.
 */
public class SoapException extends Exception {

    private static final long serialVersionUID = 1L;

    public SoapException(Throwable cause) {
        super(cause);
    }

    public SoapException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoapException(String message) {
        super(message);
    }
}
