package com.quemb.qmbform.view;


import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by pmaccamp on 8/28/2015.
 */
public class FormEditTextViewInlineFieldCell extends FormEditTextFieldCell {


    public FormEditTextViewInlineFieldCell(Context context,
                                           RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected int getResource() {
        return R.layout.edit_text_view_inline_field_cell;
    }
}
