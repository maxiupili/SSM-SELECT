package org.select.exception;

public class StockException extends SelectException {
    public StockException(String message){
        super(message);
    }
    public StockException(String message,Throwable cause){
        super(message, cause);
    }
}
