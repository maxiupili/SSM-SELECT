package org.select.exception;

public class EmptyFieldException extends SelectException {
    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
