package org.select.util;

import org.select.exception.EmptyFieldException;

public class Util {
    public static String throwIfBlank(String message,String target){
        if(target == null || (target = target.trim()).length() == 0) {
            throw new EmptyFieldException(message);
        }
        return target;

    }
    public static String $(String message,String target){
        return throwIfBlank(message,target);
    }
}
