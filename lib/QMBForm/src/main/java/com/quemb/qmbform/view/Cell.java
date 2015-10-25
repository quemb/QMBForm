package com.quemb.qmbform.view;

import com.quemb.qmbform.descriptor.FormItemDescriptor;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public abstract class Cell extends LinearLayout {

    private FormItemDescriptor mFormItemDescriptor;

    private View mDividerView;

    public Cell(Context context, FormItemDescriptor formItemDescriptor) {
        super(context);
        setFormItemDescriptor(formItemDescriptor);

        init();
        update();
        afterInit();

    }

    protected void afterInit() {

    }

    protected void init() {

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);

        int resource = getResource();
        if (resource > 0) {
            inflate(getContext(), getResource(), getSuperViewForLayoutInflation());
        }

        if (shouldAddDivider()) {
            addView(getDividerView());
        }

    }

    protected ViewGroup getSuperViewForLayoutInflation() {
        return this;
    }

    protected abstract int getResource();

    protected abstract void update();

    public FormItemDescriptor getFormItemDescriptor() {
        return mFormItemDescriptor;
    }

    public void setFormItemDescriptor(FormItemDescriptor formItemDescriptor) {

        mFormItemDescriptor = formItemDescriptor;
        mFormItemDescriptor.setCell(this);

    }

    public void onCellSelected() {

    }

    protected View getDividerView() {
        if (mDividerView == null) {
            mDividerView = new View(getContext());
            configDivider(mDividerView);
        }
        return mDividerView;
    }

    private void configDivider(View dividerView) {

        dividerView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                1
        ));

        dividerView.setBackgroundColor(getThemeValue(android.R.attr.listDivider));

    }

    protected int getThemeValue(int resource) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getContext().getTheme();
        theme.resolveAttribute(resource, typedValue, true);

        return typedValue.data;
    }

    public boolean shouldAddDivider() {
        return true;
    }

    public void lastInSection() {

    }

    protected void setDividerView(View dividerView) {
        mDividerView = dividerView;
    }

}
