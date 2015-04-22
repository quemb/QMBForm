package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditIntegerFieldCell extends FormEditTextFieldCell {

    private EditText mEditView;

    public FormEditIntegerFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {
        super.init();

        mEditView = getEditView();
        mEditView.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    @Override
    protected void updateEditView() {

        Value<Integer> value = (Value<Integer>) getRowDescriptor().getValue();
        if (value != null){
            String valueString = String.valueOf(value.getValue());
            getEditView().setText(valueString);
        }

    }


    protected void onEditTextChanged(String string) {

        try {
            Integer value = Integer.parseInt(string);
            onValueChanged(new Value<Integer>(value));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

    }

    public EditText getEditText() {
        return mEditView;
    }
}
