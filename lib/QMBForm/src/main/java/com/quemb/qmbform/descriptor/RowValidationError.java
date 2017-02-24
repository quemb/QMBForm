package com.quemb.qmbform.descriptor;

import com.quemb.qmbform.R;

import android.content.Context;

/**
 * Created by tonimoeckel on 01.12.14.
 */
public class RowValidationError {

    private int mResourceMessage;
    private RowDescriptor mRowDescriptor;

    public RowValidationError(RowDescriptor tRowDescriptor, int resourceMessage) {

        mResourceMessage = resourceMessage;
        mRowDescriptor = tRowDescriptor;
    }

    public int getResourceMessage() {
        return mResourceMessage;
    }

    public String getMessage(Context context){
        if (getResourceMessage() == R.string.validation_is_required){
            return getRowDescriptor().getTitle() + " " + context.getString(R.string.msg_x_is_required);
        }else {
            return context.getString(R.string.msg_check_form);
        }
    }

    public RowDescriptor getRowDescriptor() {
        return mRowDescriptor;
    }
}
