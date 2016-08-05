package com.quemb.qmbform.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by tonimoeckel on 18.09.14.
 */
public class FormExternalButtonFieldCell extends FormButtonFieldCell {


    public FormExternalButtonFieldCell(Context context,
                                       RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getData() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(value.getData().toString()));
            getContext().startActivity(i);
        }

    }

}
