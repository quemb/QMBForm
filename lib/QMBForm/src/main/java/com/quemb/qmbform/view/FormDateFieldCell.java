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
public class FormDateFieldCell extends FormDetailTextFieldCell {

    private TextView mTextView;

    public FormDateFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mTextView = (TextView)findViewById(R.id.textView);

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
        if (value == null || value.getValue() == null){
            value = new Value<Date>(new Date());
        }else {
            updateDateLabel(value.getValue());
        }

        final Calendar calendar = Calendar.getInstance();
        Date date = value.getValue();
        calendar.setTime(date);

        initDatePicker(calendar);

        mTextView.setEnabled(!getRowDescriptor().getDisabled());
        if(getRowDescriptor().getDisabled()) {
            mTextView.setTextColor(getResources().getColor(R.color.form_cell_disabled));
        }


    }

    protected void initDatePicker(Calendar calendar) {

    }

    public void onDateChanged(Date date) {

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, monthOfYear, dayOfMonth);
//        Date date = new Date(calendar.getTimeInMillis());

        updateDateLabel(date);

        onValueChanged(new Value<Date>(date));

    }

    protected void updateDateLabel(Date date){

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
        String s = dateFormat.format(date);
        getDetailTextView().setText(s);

    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }

    public TextView getTextView(){
        return mTextView;
    }
}
