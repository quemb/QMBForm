package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;

/**
 * Created by pmaccamp on 9/4/2015.
 */
public class FormEditIntegerInlineFieldCell extends FormEditIntegerFieldCell {
    public FormEditIntegerInlineFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_view_inline_field_cell;
    }
}
