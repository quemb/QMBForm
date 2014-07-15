package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormBooleanFieldCell extends FormBaseCell {

    private Switch mSwitch;

    public FormBooleanFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        
        mSwitch = (Switch) findViewById(R.id.switchControl);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onValueChanged(new Value<Boolean>(isChecked));
            }
        });

    }

    @Override
    protected int getResource() {
        return R.layout.boolean_field_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mSwitch.setText(title);

        Value<Boolean> value = (Value<Boolean>) getRowDescriptor().getValue();
        if (value != null){
            mSwitch.setChecked(value.getValue());
        }

    }
}
