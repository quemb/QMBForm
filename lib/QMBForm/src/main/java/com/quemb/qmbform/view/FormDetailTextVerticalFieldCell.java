package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormDetailTextVerticalFieldCell extends FormTitleFieldCell {

    private TextView mDetailTextView;

    public FormDetailTextVerticalFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mDetailTextView = (TextView)findViewById(R.id.detailTextView);

    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_vertical_field_cell;
    }

    @Override
    protected void update() {

        super.update();

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getValue() != null){
            getDetailTextView().setText(value.getValue().toString());
        }


    }

    public TextView getDetailTextView() {
        return mDetailTextView;
    }
}
