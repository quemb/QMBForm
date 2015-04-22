package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDateInlineFieldCell extends FormDateFieldCell implements
        DatePicker.OnDateChangedListener{

    private DatePicker mDatePicker;

    public FormDateInlineFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mDatePicker = (DatePicker)findViewById(R.id.datePicker);


    }

    @Override
    protected int getResource() {
        return R.layout.date_inline_field_cell;
    }


    @Override
    protected void initDatePicker(Calendar calendar) {
        mDatePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),this);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = new Date(calendar.getTimeInMillis());

        onDateChanged(date);

    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        mDatePicker.setVisibility(mDatePicker.getVisibility()==VISIBLE?GONE:VISIBLE);
//        ensureVisible();
    }

//    public void ensureVisible()
//    {
//        ListView listView = (ListView) getParent();
//        int lastSelectedIndex = listView.getSelectedItemPosition();
//        listView.setSelection(lastSelectedIndex);
//
//    }
}
