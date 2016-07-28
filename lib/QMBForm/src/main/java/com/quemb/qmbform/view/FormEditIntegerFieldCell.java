package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditIntegerFieldCell extends FormEditTextFieldCell {

    private static final String TAG = "FormEditIntegerCell";

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

        @SuppressWarnings("unchecked") Value<Integer> value = (Value<Integer>) getRowDescriptor().getValue();
        if (value != null) {
            String valueString = String.valueOf(value.getValue());
            getEditView().setText(valueString);
        }

    }


    protected void onEditTextChanged(String string) {

        try {
            Integer value = Integer.parseInt(string);
            onValueChanged(new Value<Integer>(value));
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage(), e);
        }

    }

    public EditText getEditText() {
        return mEditView;
    }
}
