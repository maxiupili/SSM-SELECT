package org.select.exception;

public class ProjectUpdateException extends SelectException {
    public ProjectUpdateException(String message) {
        super(message);
    }

    public ProjectUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
