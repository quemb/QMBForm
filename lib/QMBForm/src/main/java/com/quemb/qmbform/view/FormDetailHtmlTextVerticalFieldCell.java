package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;

/**
 * Created by schmidtma on 19.02.15.
 */
public class FormDetailHtmlTextVerticalFieldCell extends FormDetailTextFieldCell {

    public FormDetailHtmlTextVerticalFieldCell(Context context,
                                               RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void update() {

        super.update();

        if (getRowDescriptor().getHint(getContext()) != null) {
            getDetailTextView().setHint(getRowDescriptor().getHint(getContext()));
        }

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            if (value.getValue() instanceof Integer) {
                getDetailTextView().setText(String.valueOf(value.getValue()));
            } else {
                getDetailTextView().setText((CharSequence) value.getValue());
            }
        }
    }

    @Override
    protected int getResource() {
        return R.layout.detail_html_text_vertical_field_cell;
    }
}