package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by schmidtma on 19.02.15.
 */
public class FormDetailHtmlTextVerticalFieldCell extends FormDetailTextFieldCell {

    public FormDetailHtmlTextVerticalFieldCell(Context context,
                                               RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void update() {

        super.update();

        if (getRowDescriptor().getHint(getContext()) != null) {
            getDetailTextView().setHint(getRowDescriptor().getHint(getContext()));
        }

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getData() != null) {
            if (value.getData() instanceof Integer) {
                getDetailTextView().setText(String.valueOf(value.getData()));
            } else {
                getDetailTextView().setText((CharSequence) value.getData());
            }
        }
    }

    @Override
    protected int getResource() {
        return R.layout.detail_html_text_vertical_field_cell;
    }
}