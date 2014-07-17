package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.FormItemDescriptor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public abstract class Cell extends LinearLayout {

    private FormItemDescriptor mFormItemDescriptor;

    public Cell(Context context, FormItemDescriptor formItemDescriptor) {
        super(context);
        setFormItemDescriptor(formItemDescriptor);
        formItemDescriptor.setCell(this);
        init();
        update();
        afterInit();
    }

    protected void afterInit(){

    }

    protected void init(){

        inflate(getContext(), getResource(), this);

    }

    protected abstract int getResource();

    protected abstract void update();

    public FormItemDescriptor getFormItemDescriptor() {
        return mFormItemDescriptor;
    }

    public void setFormItemDescriptor(FormItemDescriptor formItemDescriptor) {
        mFormItemDescriptor = formItemDescriptor;
    }

    public void onCellSelected(){

    }

}
