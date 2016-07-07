package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTimeFieldCell extends FormDateFieldCell {

    public FormTimeFieldCell(Context context,
                             RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void updateDateLabel(Date date, boolean disabled) {

        DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(getContext());
        String s = dateFormat.format(date);

        TextView editView = getDetailTextView();
        editView.setText(s);

        if (disabled)
        {
            editView.setEnabled(false);
            setTextColor(editView, CellDescriptor.COLOR_VALUE_DISABLED);
        }

    }

}
