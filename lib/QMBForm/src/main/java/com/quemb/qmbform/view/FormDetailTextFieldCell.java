package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDetailTextFieldCell extends FormDetailTextInlineFieldCell {

    public FormDetailTextFieldCell(Context context,
                                   RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_field_cell;
    }
}
