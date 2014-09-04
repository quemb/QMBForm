package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormCheckFieldCell extends FormBaseCell {

    private CheckBox mCheckBox;

    public FormCheckFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onValueChanged(new Value<Boolean>(isChecked));
            }
        });

    }

    @Override
    protected int getResource() {
        return R.layout.check_field_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mCheckBox.setText(title);
        mCheckBox.setEnabled(!getRowDescriptor().getDisabled());

        Value<Boolean> value = (Value<Boolean>) getRowDescriptor().getValue();
        if (value != null){
            mCheckBox.setChecked(value.getValue());
        }

    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }
}
