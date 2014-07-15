package com.quemb.qmbform.descriptor;

import java.util.Date;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class Value<T> {


    private T mValue;
/*
    private String mString;
    private Number mNumber;
    private Boolean mBoolean;
    private Date mDate;

    public void set(String string){
        mString = string;
    }

    public void set(Number number){
        mNumber = number;
    }

    public void set(Boolean aBoolean){
        mBoolean = aBoolean;
    }

    public void set(Date date){
        mDate = date;
    }
*/
    public Value(){

    }

    public Value(T value){
        mValue = value;
    }

    public T getValue(){
        return mValue;
    }

    public void setValue(T value){
        mValue = value;
    }
}
