package com.quemb.qmbform.view;

import android.content.Context;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormSelectorPickerDialogFieldCell extends FormSelectorPickerDialogInlineFieldCell {


    public FormSelectorPickerDialogFieldCell(Context context,
                                             RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.selector_picker_dialog_field_cell;
    }
}
