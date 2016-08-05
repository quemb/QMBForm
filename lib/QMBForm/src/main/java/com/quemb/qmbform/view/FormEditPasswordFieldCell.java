package com.quemb.qmbform.view;

import android.content.Context;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditPasswordFieldCell extends FormEditTextFieldCell {

    public FormEditPasswordFieldCell(Context context,
                                     RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {
        super.init();

        EditText editView = getEditView();
        editView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editView.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }


}
