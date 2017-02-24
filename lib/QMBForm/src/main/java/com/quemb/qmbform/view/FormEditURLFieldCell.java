package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditURLFieldCell extends FormEditTextFieldCell {

    public FormEditURLFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {
        super.init();

        EditText editView = getEditView();
        editView.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
    }



}
