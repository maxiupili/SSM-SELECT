package org.select.exception;

public class LoginException extends SelectException {
    public LoginException(String message){
        super(message);
    }
    public LoginException(String message,Throwable cause){
        super(message,cause);
    }
}
