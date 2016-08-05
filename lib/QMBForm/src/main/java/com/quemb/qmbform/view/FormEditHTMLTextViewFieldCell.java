package com.quemb.qmbform.view;

import android.content.Context;
import android.text.Html;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditHTMLTextViewFieldCell extends FormEditTextViewFieldCell {

    public FormEditHTMLTextViewFieldCell(Context context,
                                         RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    protected void updateEditView() {

        @SuppressWarnings("unchecked") Value<String> value = (Value<String>) getRowDescriptor().getValue();
        if (value != null && value.getData() != null) {
            String valueString = value.getData();
            if (valueString != null) {
                valueString = Html.fromHtml(valueString).toString();
            }
            getEditView().setText(valueString);
        }

    }
}
