package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTitleFieldCell extends FormBaseCell {
    private TextView mTextView;

    public FormTitleFieldCell(Context context,
                              RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();

        mTextView = (TextView) findViewById(R.id.textView);
        setStyleId(mTextView, CellDescriptor.APPEARANCE_TEXT_LABEL, CellDescriptor.COLOR_LABEL);
    }

    @Override
    protected int getResource() {
        return R.layout.text_field_cell;
    }

    @Override
    protected void update() {
        String title = getFormItemDescriptor().getTitle();
        mTextView.setText(title);
        mTextView.setVisibility(title == null ? GONE : VISIBLE);


        if (getRowDescriptor().getDisabled()) {
            getRowDescriptor().setOnFormRowClickListener(null);

            setTextColor(mTextView, CellDescriptor.COLOR_LABEL_DISABLED);
        }
    }

    public TextView getTextView() {
        return mTextView;
    }
}
