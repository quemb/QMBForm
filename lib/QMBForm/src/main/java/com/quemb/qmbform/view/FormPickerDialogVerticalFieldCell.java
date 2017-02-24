package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.exceptions.NoDataSourceException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormPickerDialogVerticalFieldCell extends FormPickerDialogFieldCell {


    public FormPickerDialogVerticalFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_vertical_field_cell;
    }
}
