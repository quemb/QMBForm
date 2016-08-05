package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormCheckFieldCell extends FormBaseCell {

    private CheckBox mCheckBox;

    public FormCheckFieldCell(Context context,
                              RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        setStyleId(mCheckBox, CellDescriptor.APPEARANCE_TEXT_LABEL, CellDescriptor.COLOR_LABEL);

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

        if (getRowDescriptor().getDisabled())
        {
            mCheckBox.setEnabled(false);
            setTextColor(mCheckBox, CellDescriptor.COLOR_LABEL_DISABLED);
        }
        else
            mCheckBox.setEnabled(true);

        @SuppressWarnings("unchecked") Value<Boolean> value = (Value<Boolean>) getRowDescriptor().getValue();
        if (value != null) {
            mCheckBox.setChecked(value.getData());
        }

    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }
}
