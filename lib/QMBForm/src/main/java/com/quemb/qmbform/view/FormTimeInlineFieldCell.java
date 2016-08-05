package com.quemb.qmbform.view;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.quemb.qmbform.R;
import com.quemb.qmbform.TimePickerInline;
import com.quemb.qmbform.descriptor.RowDescriptor;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTimeInlineFieldCell extends FormTimeFieldCell implements
        TimePicker.OnTimeChangedListener {

    private TimePickerInline mTimePicker;

    public FormTimeInlineFieldCell(Context context,
                                   RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {

        super.init();
        mTimePicker = (TimePickerInline) findViewById(R.id.timePicker);


    }

    @Override
    protected int getResource() {
        return R.layout.time_inline_field_cell;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initDatePicker(Calendar calendar) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            mTimePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            mTimePicker.setMinute(calendar.get(Calendar.MINUTE));
        }
        else
        {
            mTimePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            mTimePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }
        mTimePicker.setOnTimeChangedListener(this);

        if (DateFormat.is24HourFormat(getContext()))
            mTimePicker.setIs24HourView(true);

    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        mTimePicker.setVisibility(mTimePicker.getVisibility() == VISIBLE ? GONE : VISIBLE);

    }

    public TimePicker getTimePicker() {
        return mTimePicker;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        Date date = new Date();
        date.setTime(TimeUnit.HOURS.toMillis(hourOfDay) + TimeUnit.MINUTES.toMillis(minute));

        onDateChanged(date);

    }
}
