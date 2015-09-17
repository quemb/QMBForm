package com.quemb.qmbform.view;


import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by pmaccamp on 8/28/2015.
 */
public class FormEditEmailInlineFieldCell extends FormEditEmailFieldCell {

    public FormEditEmailInlineFieldCell(Context context,
                                        RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_inline_field_cell;
    }

}
