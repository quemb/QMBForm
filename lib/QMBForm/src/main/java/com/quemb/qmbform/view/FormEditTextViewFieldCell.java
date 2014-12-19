package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditTextViewFieldCell extends FormEditTextFieldCell {


    public FormEditTextViewFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }



    @Override
    protected int getResource() {
        return R.layout.edit_text_view_field_cell;
    }
}
