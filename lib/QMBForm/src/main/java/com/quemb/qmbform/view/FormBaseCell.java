package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.OnValueChangeListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public abstract class FormBaseCell extends Cell  {


    public FormBaseCell(Context context, RowDescriptor rowDescriptor) {

        super(context, rowDescriptor);

    }

    @Override
    protected void init() {
        super.init();

        if (getRowDescriptor().getValue() != null){
            getRowDescriptor().getValue().setOnValueChangeListener(new OnValueChangeListener() {
                @Override
                public void onChange(Object value) {
                    update();
                }
            });
        }

    }

    public RowDescriptor getRowDescriptor() {
        return (RowDescriptor) getFormItemDescriptor();
    }

    public void onValueChanged(Value<?> newValue){

        RowDescriptor row = getRowDescriptor();
        Value<?> oldValue = row.getValue();
        if (oldValue == null || newValue == null || !newValue.getValue().equals(oldValue.getValue())){
            OnFormRowValueChangedListener listener = getRowDescriptor().getSectionDescriptor().getFormDescriptor().getOnFormRowValueChangedListener();
            row.setValue(newValue);
            if (listener != null){
                listener.onValueChanged(row , oldValue, newValue);
            }
        }

    }
}
