package com.quemb.qmbform.exceptions;

/**
 * Created by tonimoeckel on 01.08.14.
 */
public class NoDataSourceException extends RuntimeException {

    public NoDataSourceException(){
        super("No Data Source Defined");
    }

}
