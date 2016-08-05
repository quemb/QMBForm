package com.quemb.qmbform.view;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import com.quemb.qmbform.descriptor.RowDescriptor;

import java.util.Calendar;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTimeDialogFieldCell extends FormTimeFieldCell implements
        TimePickerDialog.OnTimeSetListener {

    private Calendar mCalendar;

    public FormTimeDialogFieldCell(Context context,
                                   RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void initDatePicker(Calendar calendar) {

        mCalendar = calendar;
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        TimePickerDialog dialog = new TimePickerDialog(getContext(), this, getCalendar().get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
        dialog.show();

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar calendar = getCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        onDateChanged(calendar.getTime());

    }

    public Calendar getCalendar() {
        return mCalendar;
    }
}
