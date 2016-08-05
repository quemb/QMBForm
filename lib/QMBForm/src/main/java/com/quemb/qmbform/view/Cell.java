package com.quemb.qmbform.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.utils.Styling;

import java.util.HashMap;

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

    // ===== Colors ===========

    /**
     * Set the style ID for the specified 'styleConfig' parameter if defined in CellDescriptor,
     * or apply the default Style Id and the default android:textColor.
     */
    protected boolean setStyleId(TextView textView, String styleConfig, String colorConfig)
    {
        boolean styleFound = false;

        // Get textAppearance from the cellConfig (APPEARANCE_XXX) in FormItemDescriptor

        HashMap<String,Object> cellConfig = null;
        FormItemDescriptor itemDescriptor = getFormItemDescriptor();
        if (itemDescriptor != null)
        {
            cellConfig = itemDescriptor.getCellConfig();
            if (cellConfig != null)
            {
                Object configId = cellConfig.get(styleConfig);
                if (configId != null && configId instanceof Integer)
                {
                    // If exists, apply style and return true

                    @StyleRes int styleId = (Integer) configId;
                    Styling.setTextAppearance(textView, styleId);

                    styleFound = true;
                }
            }
        }

        // If defined, default color is set from cellConfig 'COLOR_XXX' parameter.
        // Otherwise, save the default android color (before applying style).

        int defaultColor;
        if (cellConfig != null && colorConfig != null)
        {
            Object configId = cellConfig.get(colorConfig);
            if (configId != null && configId instanceof Integer)
                defaultColor =  (Integer) configId;
            else
                defaultColor = getDefaultColor(colorConfig);
        }
        else
            defaultColor = getDefaultColor(colorConfig);

        textView.setTextColor(defaultColor);

        return styleFound;
    }

    /**
     * Get the default color for TextView (android:textColor in theme).
     * Note that default EditText color is android:editTextColor in theme.
     * Force to default TextView color for CheckBox and Switch views.
     */
    private int getDefaultColor(String colorConfig)
    {
        if (colorConfig != null && colorConfig.equals(CellDescriptor.COLOR_VALUE))
        {
            return getThemeValue(android.R.attr.editTextColor);
        }
        return getThemeValue(android.R.attr.textColor);
    }

    /**
     * Set the TextView color from the cellConfig using 'colorConfig' parameter, if defined in CellDescriptor.
     * Only used for COLOR_XXX_DISABLED colors.
     */
    protected boolean setTextColor(TextView textView, String colorConfig)
    {
        // Get color from the cellConfig in FormItemDescriptor

        FormItemDescriptor itemDescriptor = getFormItemDescriptor();
        if (itemDescriptor != null)
        {
            HashMap<String,Object> config = itemDescriptor.getCellConfig();
            if (config != null && config.containsKey(colorConfig))
            {
                Object configColor = config.get(colorConfig);
                if (configColor instanceof Integer)
                {
                    textView.setTextColor(((Integer) configColor).intValue());
                    return true;
                }
            }
        }
        return false;
    }
}
