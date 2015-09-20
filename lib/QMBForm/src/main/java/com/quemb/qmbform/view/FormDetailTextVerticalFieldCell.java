package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDetailTextVerticalFieldCell extends FormDetailTextFieldCell {

    public FormDetailTextVerticalFieldCell(Context context,
                                           RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_vertical_field_cell;
    }
}
