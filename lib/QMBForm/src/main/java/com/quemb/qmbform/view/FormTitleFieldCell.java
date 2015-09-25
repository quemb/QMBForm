package com.quemb.qmbform.view;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormTitleFieldCell extends FormBaseCell {
    private TextView mLabelTextView;

    public FormTitleFieldCell(Context context,
                              RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();
        mLabelTextView = (TextView) findViewById(R.id.labelTextView);
        mLabelTextView.setTextColor(R.attr.editTextColor);
        mLabelTextView.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Body2);
        mLabelTextView.setTypeface(mLabelTextView.getTypeface(), Typeface.BOLD);
    }

    @Override
    protected int getResource() {
        return R.layout.text_field_cell;
    }

    protected void updateTitle() {
        String title = getFormItemDescriptor().getTitle();
        mLabelTextView.setText(title);
        mLabelTextView.setVisibility(title == null ? GONE : VISIBLE);


        if (getRowDescriptor().getDisabled()) {
            getRowDescriptor().setOnFormRowClickListener(null);
            mLabelTextView.setTextColor(getResources().getColor(R.color.form_cell_disabled));
        }
    }

    @Override
    protected void update() {
        updateTitle();
    }

    public TextView getLabelTextView() {
        return mLabelTextView;
    }
}
