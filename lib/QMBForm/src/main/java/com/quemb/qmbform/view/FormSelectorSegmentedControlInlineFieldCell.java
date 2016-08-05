package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by pmaccamp on 9/8/2015.
 */
public class FormSelectorSegmentedControlInlineFieldCell extends FormSelectorSegmentedControlFieldCell {

    public FormSelectorSegmentedControlInlineFieldCell(Context context, RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.selector_segmented_control_inline_field_cell;
    }
}