package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDateInlineFieldCell extends FormBaseCell implements
        DatePicker.OnDateChangedListener{

    private TextView mTextView;
    private TextView mDetailTextView;
    private DatePicker mDatePicker;

    public FormDateInlineFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mTextView = (TextView)findViewById(R.id.textView);
        mDetailTextView = (TextView)findViewById(R.id.detailTextView);
        mDatePicker = (DatePicker)findViewById(R.id.datePicker);


    }

    @Override
    protected int getResource() {
        return R.layout.date_field_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mTextView.setText(title);
        mTextView.setVisibility(title == null?GONE:VISIBLE);

        Value<Date> value = (Value<Date>) getRowDescriptor().getValue();
        if (value == null){
            value = new Value<Date>(new Date());
        }else {
            updateDateLabel(value.getValue());
        }

        final Calendar calendar = Calendar.getInstance();
        Date date = value.getValue();
        calendar.setTime(date);
        mDatePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),this);

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = new Date(calendar.getTimeInMillis());

        updateDateLabel(date);

        onValueChanged(new Value<Date>(date));

    }

    private void updateDateLabel(Date date){

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
        String s = dateFormat.format(date);
        mDetailTextView.setText(s);

    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        mDatePicker.setVisibility(mDatePicker.getVisibility()==VISIBLE?GONE:VISIBLE);
        ensureVisible();
    }

    public void ensureVisible()
    {
        ListView listView = (ListView) getParent();
        int lastSelectedIndex = listView.getSelectedItemPosition();
        listView.setSelection(lastSelectedIndex);

    }
}
