package com.quemb.qmbform.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.utils.Styling;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormBooleanFieldCell extends FormBaseCell {

    private Switch mSwitch;

    public FormBooleanFieldCell(Context context,
                                RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();

        mSwitch = (Switch) findViewById(R.id.switchControl);
        setStyleId(mSwitch, CellDescriptor.APPEARANCE_TEXT_LABEL, CellDescriptor.COLOR_LABEL);

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

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();

        mSwitch.setText(title);
        if (getRowDescriptor().getDisabled())
        {
            mSwitch.setEnabled(false);
            if (setTextColor(mSwitch, CellDescriptor.COLOR_LABEL_DISABLED) == false)
                mSwitch.setTextColor(Styling.getColorFromAttr(mSwitch.getContext(), android.R.attr.textColor));
        }
        else
            mSwitch.setEnabled(true);

        @SuppressWarnings("unchecked") Value<Boolean> value = (Value<Boolean>) getRowDescriptor().getValue();
        if (value != null && value.getData() != null) {
            mSwitch.setChecked(value.getData());
        }

    }

    public Switch getSwitch() {
        return mSwitch;
    }
}
