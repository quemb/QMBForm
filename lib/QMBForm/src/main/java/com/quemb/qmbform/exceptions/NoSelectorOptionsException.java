package com.quemb.qmbform.exceptions;

/**
 * Created by pmaccamp on 9/23/2015.
 */
public class NoSelectorOptionsException extends RuntimeException {
    public NoSelectorOptionsException() {
        super("No Data Source Defined");
    }
}
