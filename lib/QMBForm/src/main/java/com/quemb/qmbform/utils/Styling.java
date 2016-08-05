package com.quemb.qmbform.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import com.quemb.qmbform.R;

/**
 * Android style utilities
 * Created by MTL / PTN on 04/08/16.
 */
public class Styling
{
    @SuppressWarnings("deprecation")
    public static void setTextAppearance(TextView textView, int styleId)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            textView.setTextAppearance(styleId);
        else
            textView.setTextAppearance(textView.getContext(), styleId);
    }

    /**
     * Apply the standard EditText appearance to the TextView
     * (only if no specific style is defined for APPEARANCE_TEXT_VALUE)
     */
    public static void applyEditTextStyle(TextView textView)
    {
        // Get the android:textAppearance item from R.style.Widget_AppCompat_EditText (default EditText style)
        int editTextAppearanceId = getStyleItemResourceId(textView.getContext(),
                R.style.Widget_AppCompat_EditText, android.R.attr.textAppearance, android.R.attr.textAppearanceMediumInverse);

        // Get the android:textSize item from retrieved textAppearance style
        DisplayMetrics displayMetrics = textView.getContext().getResources().getDisplayMetrics();
        float editSize = getStyleItemDimension(textView.getContext(),
                editTextAppearanceId, android.R.attr.textSize, 18f * (displayMetrics.densityDpi / 160f));

        // Set inline text size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, editSize);
    }

    /**
     * Get a item defined as resource ID from an android style
     */
    public static int getStyleItemResourceId(Context context, int styleId, int attributeId, int defValue)
    {
        // attribute to retrieve
        int[] attrs = {attributeId}; // {android.R.attr.textColor, android.R.attr.text};

        // Parse the style, using Context.obtainStyledAttributes()
        TypedArray ta = context.obtainStyledAttributes(styleId, attrs);

        // Fetch the resource, color or text defined in your style
        int styleItem = ta.getResourceId(0, defValue); // android.R.attr.textAppearanceMediumInverse);

        // Recycle the TypedArray
        ta.recycle();

        return styleItem;
    }

    /**
     * Get a item defined as dimension from an android style
     */
    public static float getStyleItemDimension(Context context, int styleId, int attributeId, float defValue)
    {
        // attribute to retrieve
        int[] attrs = {attributeId};

        // Parse the style, using Context.obtainStyledAttributes()
        TypedArray ta = context.obtainStyledAttributes(styleId, attrs);

        // Fetch the text from your style like this.
        float dimensionItem = ta.getDimension(0, defValue);

        // Recycle the TypedArray
        ta.recycle();

        return dimensionItem;
    }

    /**
     * Get color value from attribute ID
     */
    public static int getColorFromAttr(Context context, int attrResId)
    {
        TypedValue typedValue = new TypedValue();

        if (context.getTheme().resolveAttribute(attrResId, typedValue, true))
            return typedValue.data;

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{attrResId});
        int color = a.getColor(0, Color.RED);
        a.recycle();
        return color;
    }
}
