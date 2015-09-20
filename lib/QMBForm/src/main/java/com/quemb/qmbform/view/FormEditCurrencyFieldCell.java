package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditCurrencyFieldCell extends FormEditNumberFieldCell {

    public FormEditCurrencyFieldCell(Context context,
                                     RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void updateEditView() {

        Value<Number> value = (Value<Number>) getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            String valueString = format.format(value.getValue());//String.valueOf(value.getValue());
            getEditView().setText(valueString);
        }

    }


    protected void onEditTextChanged(String string) {

        try {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Number floatValue = (Number) format.parse(string);
            onValueChanged(new Value<Number>(floatValue));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }


    }

}
