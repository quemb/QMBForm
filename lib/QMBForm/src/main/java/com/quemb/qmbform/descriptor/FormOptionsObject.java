package com.quemb.qmbform.descriptor;

import java.util.ArrayList;

/**
 * Created by pmaccamp on 9/8/2015.
 */
public class FormOptionsObject {
    private Object mValue;
    private String mFormDisplayText;

    private FormOptionsObject(Object value, String formDisplayText) {
        mFormDisplayText = formDisplayText;
        mValue = value;
    }

    public static FormOptionsObject createFormOptionsObject(Object value, String displayText) {
        return new FormOptionsObject(value, displayText);
    }

    public static FormOptionsObject formOptionsObjectFromArrayWithValue(Object searchValue, ArrayList<FormOptionsObject> options) {
        for (FormOptionsObject option : options) {
            if (option.mValue.equals(searchValue)) {
                return option;
            }
        }
        return null;
    }

    public static int indexOfFormOptionsObjectFromArrayWithValue(Object searchValue, ArrayList<FormOptionsObject> options) {
        int counter = 0;
        for (FormOptionsObject option : options) {
            if (option.mValue.equals(searchValue)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public static FormOptionsObject formOptionsObjectFromArrayWithDisplayText(String searchText, ArrayList<FormOptionsObject> options) {
        for (FormOptionsObject option : options) {
            if (option.mFormDisplayText.equals(searchText)) {
                return option;
            }
        }
        return null;
    }

    public static int indexOfFormOptionsObjectFromArrayWithDisplayText(String searchText, ArrayList<FormOptionsObject> options) {
        int counter = 0;
        for (FormOptionsObject option : options) {
            if (option.mFormDisplayText.equals(searchText)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public Object getValue() {
        return mValue;
    }

    public String getDisplayText() {
        return mFormDisplayText;
    }
}
