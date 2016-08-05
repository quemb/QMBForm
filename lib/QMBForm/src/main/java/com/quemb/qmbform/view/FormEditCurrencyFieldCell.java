package com.quemb.qmbform.view;

import android.content.Context;
import android.util.Log;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditCurrencyFieldCell extends FormEditNumberFieldCell {

    private static final String TAG = "FormEditCurrencyFieldCell";

    public FormEditCurrencyFieldCell(Context context,
                                     RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void updateEditView() {

        @SuppressWarnings("unchecked") Value<Number> value = (Value<Number>) getRowDescriptor().getValue();
        if (value != null && value.getData() != null) {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            String valueString = format.format(value.getData());//String.valueOf(value.getValue());
            getEditView().setText(valueString);
        }

    }


    protected void onEditTextChanged(String string) {

        try {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Number floatValue = (Number) format.parse(string);
            onValueChanged(new Value<Number>(floatValue));
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (ClassCastException e) {
            Log.e(TAG, e.getMessage(), e);
        }


    }

}
