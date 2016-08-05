package com.quemb.qmbform.view;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quemb.qmbform.DatePickerInline;
import com.quemb.qmbform.R;
import com.quemb.qmbform.TimePickerInline;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.utils.Styling;

import java.util.Calendar;
import java.util.Date;

/**
 * A cell to select date and time inline.
 */
public class FormDateTimeInlineFieldCell extends FormDateFieldCell implements
        DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener
{
    private DatePickerInline mDatePicker;
    private TimePickerInline mTimePicker;

    public FormDateTimeInlineFieldCell(Context context,
                                       RowDescriptor<?> rowDescriptor)
    {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mDatePicker = (DatePickerInline) findViewById(R.id.datePicker);
        mTimePicker = (TimePickerInline) findViewById(R.id.timePicker);

    }

    @Override
    protected int getResource() {
        return R.layout.datetime_inline_field_cell;
    }


    @Override
    @SuppressWarnings("deprecation")
    protected void initDatePicker(Calendar calendar)
    {
        mDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);

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

    public TimePicker getTimePicker() {
        return mTimePicker;
    }

    @Override
    public void onCellSelected()
    {
        super.onCellSelected();

        boolean visible = (mDatePicker.getVisibility() == VISIBLE);
        mDatePicker.setVisibility(visible ? GONE : VISIBLE);
        mTimePicker.setVisibility(visible ? GONE : VISIBLE);

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        int hourOfDay, minute;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            hourOfDay = mTimePicker.getHour();
            minute = mTimePicker.getMinute();
        }
        else
        {
            hourOfDay = mTimePicker.getCurrentHour();
            minute = mTimePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute, 0);

        Date date = new Date(calendar.getTimeInMillis());
        onDateChanged(date);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), hourOfDay, minute, 0);

        Date date = new Date(calendar.getTimeInMillis());
        onDateChanged(date);
    }

    protected void updateDateLabel(Date date, boolean disabled)
    {
        String sDate = android.text.format.DateFormat.getDateFormat(getContext()).format(date);
        String sTime = android.text.format.DateFormat.getTimeFormat(getContext()).format(date);

        TextView editView = getDetailTextView();
        editView.setText(sDate + " " + sTime);

        // Manage the 'disabled' state for value

        if (disabled)
        {
            editView.setEnabled(false);
            if (setTextColor(editView, CellDescriptor.COLOR_VALUE_DISABLED) == false)
                editView.setTextColor(Styling.getColorFromAttr(editView.getContext(), android.R.attr.textColor));
        }
    }
}
