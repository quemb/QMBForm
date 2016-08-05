package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by pmaccamp on 9/4/2015.
 */
public class FormSpinnerInlineFieldCell extends FormSpinnerFieldCell {
    public FormSpinnerInlineFieldCell(Context context, RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.spinner_inline_field_cell;
    }

}
