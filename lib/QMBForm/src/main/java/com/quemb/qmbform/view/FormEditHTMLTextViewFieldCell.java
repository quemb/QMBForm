package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditHTMLTextViewFieldCell extends FormEditTextViewFieldCell {

    public FormEditHTMLTextViewFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    protected void updateEditView() {

        Value<String> value = (Value<String>) getRowDescriptor().getValue();
        if (value != null && value.getValue() != null){
            String valueString = value.getValue();
            if (valueString != null){
                valueString = Html.fromHtml(valueString).toString();
            }
            getEditView().setText(valueString);
        }

    }
}
