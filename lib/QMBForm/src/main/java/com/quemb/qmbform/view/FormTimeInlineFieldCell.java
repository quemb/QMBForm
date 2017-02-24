package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTimeInlineFieldCell extends FormTimeFieldCell implements
        TimePicker.OnTimeChangedListener {

    private TimePicker mTimePicker;

    public FormTimeInlineFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {

        super.init();
        mTimePicker = (TimePicker)findViewById(R.id.timePicker);


    }

    @Override
    protected int getResource() {
        return R.layout.time_inline_field_cell;
    }

    @Override
    protected void initDatePicker(Calendar calendar) {

        getTimePicker().setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        getTimePicker().setCurrentMinute(calendar.get(Calendar.MINUTE));
        getTimePicker().setOnTimeChangedListener(this);
        getTimePicker().setIs24HourView(true);

    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        getTimePicker().setVisibility(getTimePicker().getVisibility()==VISIBLE?GONE:VISIBLE);

    }

    public TimePicker getTimePicker() {
        return mTimePicker;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        Date date = new Date();
        date.setTime(TimeUnit.HOURS.toMillis(hourOfDay)+TimeUnit.MINUTES.toMillis(minute));

        onDateChanged(date);

    }
}
