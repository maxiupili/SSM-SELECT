package org.select.exception;

public class RepeatSelectException extends  SelectException {
    public RepeatSelectException(String message){
        super(message);
    }
    public RepeatSelectException(String message,Throwable cause){
        super(message,cause);
    }
}
