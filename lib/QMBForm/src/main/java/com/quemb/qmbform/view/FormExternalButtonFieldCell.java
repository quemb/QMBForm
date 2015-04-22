package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by tonimoeckel on 18.09.14.
 */
public class FormExternalButtonFieldCell extends FormButtonFieldCell {


    public FormExternalButtonFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        Value<String> value = getRowDescriptor().getValue();
        if (value != null && value.getValue() != null){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(value.getValue()));
            getContext().startActivity(i);
        }

    }

}
