package com.quemb.qmbform.descriptor;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 01.12.14.
 */
public class FormValidation {

    private ArrayList<RowValidationError> mRowValidationErrors = new ArrayList<RowValidationError>();

    public ArrayList<RowValidationError> getRowValidationErrors() {
        return mRowValidationErrors;
    }

    public boolean isValid(){

        return getRowValidationErrors().size()==0;
    }

    public RowValidationError getFirstValidationError() {
        if (getRowValidationErrors().size()>0){
            return getRowValidationErrors().get(0);
        }

        return null;
    }
}
