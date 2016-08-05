package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.utils.Styling;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTimeFieldCell extends FormDateFieldCell {

    public FormTimeFieldCell(Context context,
                             RowDescriptor<?> rowDescriptor) {
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
            if (setTextColor(editView, CellDescriptor.COLOR_VALUE_DISABLED) == false)
                editView.setTextColor(Styling.getColorFromAttr(editView.getContext(), android.R.attr.textColor));
        }

    }

}
