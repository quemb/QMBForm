package com.quemb.qmbform.descriptor;

import android.content.Context;

/**
 * Created by tonimoeckel on 01.12.14.
 */
public class RowValidationError {

    private int mResourceMessage;
    private RowDescriptor<?> mRowDescriptor;

    public RowValidationError(RowDescriptor<?> tRowDescriptor, int resourceMessage) {
        mResourceMessage = resourceMessage;
        mRowDescriptor = tRowDescriptor;
    }

    public int getResourceMessage() {
        return mResourceMessage;
    }

    public String getMessage(Context context) {
        return getRowDescriptor().getTitle() + " " +
                context.getString(getResourceMessage());
    }

    public RowDescriptor<?> getRowDescriptor() {
        return mRowDescriptor;
    }
}
