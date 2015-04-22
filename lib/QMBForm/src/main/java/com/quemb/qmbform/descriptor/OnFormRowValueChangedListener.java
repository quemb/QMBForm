package com.quemb.qmbform.descriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public interface OnFormRowValueChangedListener {

    public void onValueChanged(RowDescriptor rowDescriptor, Value<?> oldValue, Value<?> newValue);

}
