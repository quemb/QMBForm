package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.SectionDescriptor;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class SectionCell extends Cell {

    private SectionDescriptor mSectionDescriptor;

    private TextView mTextView;

    public SectionCell(Context context,
                       SectionDescriptor sectionDescriptor) {
        super(context, sectionDescriptor);
    }


    @Override
    protected void init() {

        super.init();

        setClickable(false);
        setEnabled(false);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setTextColor(getThemeValue(R.attr.colorAccent));

    }

    @Override
    protected int getResource() {
        return R.layout.section_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mTextView.setText(title);

    }

    @Override
    public boolean shouldAddDivider() {
        return false;
    }

    public SectionDescriptor getSectionDescriptor() {
        return (SectionDescriptor) getFormItemDescriptor();
    }

}
