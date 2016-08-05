package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.utils.Styling;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDateFieldCell extends FormDetailTextInlineFieldCell {

    private TextView mTextView;

    public FormDateFieldCell(Context context,
                             RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mTextView = (TextView) findViewById(R.id.textView);

        setStyleId(mTextView, CellDescriptor.APPEARANCE_TEXT_LABEL, CellDescriptor.COLOR_LABEL);
        mTextView.setEnabled(false);

    }

    @Override
    protected int getResource() {
        return R.layout.date_field_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mTextView.setText(title);
        mTextView.setVisibility(title == null ? GONE : VISIBLE);

        @SuppressWarnings("unchecked") Value<Date> value = (Value<Date>) getRowDescriptor().getValue();
        if (value == null || value.getData() == null) {
            value = new Value<Date>(new Date());
        } else {
            updateDateLabel(value.getData(), getRowDescriptor().getDisabled());
        }

        final Calendar calendar = Calendar.getInstance();
        Date date = value.getData();
        calendar.setTime(date);

        initDatePicker(calendar);

        if (getRowDescriptor().getDisabled())
        {
            setTextColor(mTextView, CellDescriptor.COLOR_LABEL_DISABLED);

            setClickable(false);
            setEnabled(false);
        }

    }

    protected void initDatePicker(Calendar calendar) {

    }

    public void onDateChanged(Date date) {

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, monthOfYear, dayOfMonth);
//        Date date = new Date(calendar.getTimeInMillis());

        updateDateLabel(date, false);

        onValueChanged(new Value<Date>(date));

    }

    protected void updateDateLabel(Date date, boolean disabled) {

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
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

    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }

    public TextView getTextView() {
        return mTextView;
    }
}
