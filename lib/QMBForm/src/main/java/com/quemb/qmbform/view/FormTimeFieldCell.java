package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
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
    protected void updateDateLabel(Date date){

        DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(getContext());
        String s = dateFormat.format(date);
        getDetailTextView().setText(s);

    }

}
