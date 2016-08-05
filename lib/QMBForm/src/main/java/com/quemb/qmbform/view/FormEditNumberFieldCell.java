package com.quemb.qmbform.view;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditNumberFieldCell extends FormEditTextFieldCell {

    private static final String TAG = "FormEditNumberFieldCell";

    public FormEditNumberFieldCell(Context context,
                                   RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {
        super.init();

        EditText editView = getEditView();
        editView.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    @Override
    protected void updateEditView() {

        @SuppressWarnings("unchecked") Value<Number> value = (Value<Number>) getRowDescriptor().getValue();
        if (value != null) {
            String valueString = String.valueOf(value.getData());
            getEditView().setText(valueString);
        }

    }


    protected void onEditTextChanged(String string) {

        try {
            Float floatValue = Float.parseFloat(string);
            onValueChanged(new Value<Number>(floatValue));
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage(), e);
        }


    }
}
