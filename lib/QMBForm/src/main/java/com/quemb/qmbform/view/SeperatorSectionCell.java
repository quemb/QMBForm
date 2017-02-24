package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by tonimoeckel on 18.02.15.
 */
public class SeperatorSectionCell extends FormBaseCell {


    public SeperatorSectionCell(Context context,
                                RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    public void init() {

        super.init();

        setOrientation(LinearLayout.VERTICAL);
        setClickable(false);
        setEnabled(false);

    }

    @Override
    public boolean shouldAddDivider() {
        return false;
    }

    @Override
    protected int getResource() {
        return R.layout.section_seperator_cell;
    }

    @Override
    protected void update() {

    }

}
