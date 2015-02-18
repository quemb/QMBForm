package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
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
        setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                22
        ));
        setBackgroundResource(R.drawable.section_seperator_background);
        setClickable(false);

    }

    @Override
    public boolean shouldAddDivider() {
        return false;
    }

    @Override
    protected int getResource() {
        return 0;
    }

    @Override
    protected void update() {

    }
}
