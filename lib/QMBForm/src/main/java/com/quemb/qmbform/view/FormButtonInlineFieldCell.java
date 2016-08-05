package com.quemb.qmbform.view;


import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by pmaccamp on 8/28/2015.
 */
public class FormButtonInlineFieldCell extends FormButtonFieldCell {


    public FormButtonInlineFieldCell(Context context,
                                     RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.button_inline_field_cell;
    }
}
