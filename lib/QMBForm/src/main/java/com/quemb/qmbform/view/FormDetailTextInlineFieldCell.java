package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.utils.Styling;

/**
 * Created by pmaccamp on 9/4/2015.
 */
public class FormDetailTextInlineFieldCell extends FormTitleFieldCell {

    private TextView mDetailTextView;

    public FormDetailTextInlineFieldCell(Context context,
                                         RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mDetailTextView = (TextView) findViewById(R.id.detailTextView);
        if (setStyleId(mDetailTextView, CellDescriptor.APPEARANCE_TEXT_VALUE, CellDescriptor.COLOR_VALUE) == false)
        {
            // If no specific style is defined for APPEARANCE_TEXT_VALUE,
            // set inline text size to the default EditText size.
            Styling.applyEditTextStyle(mDetailTextView);
        }
    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_inline_field_cell;
    }

    @Override
    protected void update() {
        super.update();

        if (getRowDescriptor().getHint(getContext()) != null) {
            mDetailTextView.setHint(getRowDescriptor().getHint(getContext()));
        }

        if (getRowDescriptor().getDisabled())
        {
            if (setTextColor(mDetailTextView, CellDescriptor.COLOR_VALUE_DISABLED) == false)
                mDetailTextView.setTextColor(Styling.getColorFromAttr(mDetailTextView.getContext(), android.R.attr.textColor));
        }

        mDetailTextView.setText(displayedValue(getRowDescriptor().getValue()));

    }

    public TextView getDetailTextView() {
        return mDetailTextView;
    }

    /**
     * Return the String to be displayed
     */
    protected <T> String displayedValue(Value<T> value)
    {
        if (value != null)
        {
            T data = value.getData();

            if (data == null)
                return null;
            else if (data instanceof String)
            {
                @SuppressWarnings("unchecked") String stringData = (String) data;
                return stringData;
            }
            else
                return String.valueOf(data);
        }
        return null;
    }
}
