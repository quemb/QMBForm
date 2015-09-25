package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.SectionDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class SectionCell extends Cell {

    private SectionDescriptor mSectionDescriptor;

    private TextView mLabelTextView;

    public SectionCell(Context context,
                       SectionDescriptor sectionDescriptor) {
        super(context, sectionDescriptor);
    }


    @Override
    protected void init() {

        super.init();

        setClickable(false);

        mLabelTextView = (TextView) findViewById(R.id.labelTextView);
        mLabelTextView.setTextColor(getThemeValue(R.attr.colorAccent));

    }

    @Override
    protected int getResource() {
        return R.layout.section_cell;
    }

    @Override
    protected void update() {
        String title = getFormItemDescriptor().getTitle();
        mLabelTextView.setText(title.toUpperCase());
    }

    @Override
    public boolean shouldAddDivider() {
        return false;
    }

    public SectionDescriptor getSectionDescriptor() {
        return (SectionDescriptor) getFormItemDescriptor();
    }

}
