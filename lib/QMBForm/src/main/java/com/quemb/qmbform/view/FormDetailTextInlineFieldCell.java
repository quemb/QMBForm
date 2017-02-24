package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by pmaccamp on 9/4/2015.
 */
public class FormDetailTextInlineFieldCell extends FormTitleFieldCell {

    private TextView mDetailTextView;

    public FormDetailTextInlineFieldCell(Context context,
                                         RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mDetailTextView = (TextView) findViewById(R.id.detailTextView);
        if (setStyleId(mDetailTextView, CellDescriptor.APPEARANCE_TEXT_VALUE, CellDescriptor.COLOR_VALUE) == false)
        {
            // If no specific style is defined for APPEARANCE_TEXT_VALUE,
            // set inline text size to the default EditText size.

            // Get the android:textAppearance item from R.style.Widget_AppCompat_EditText (default EditText style)
            int editTextAppearanceId = getStyleItemResourceId(mDetailTextView.getContext(),
                    R.style.Widget_AppCompat_EditText, android.R.attr.textAppearance, android.R.attr.textAppearanceMediumInverse);

            // Get the android:textSize item from retrieved textAppearance style
            DisplayMetrics displayMetrics = mDetailTextView.getContext().getResources().getDisplayMetrics();
            float editSize = getStyleItemDimension(mDetailTextView.getContext(),
                    editTextAppearanceId, android.R.attr.textSize, 18f * (displayMetrics.densityDpi / 160f));

            // Set inline text size
            mDetailTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, editSize);
        }
    }

    @Override
    protected int getResource() {
        return R.layout.detail_text_inline_field_cell;
    }

    @Override
    protected void update() {
        super.update();

        if (getRowDescriptor().getHint(getContext()) != null) {
            getDetailTextView().setHint(getRowDescriptor().getHint(getContext()));
        }

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            if (value.getValue() instanceof String) {
                getDetailTextView().setText((String) value.getValue());
            } else {
                getDetailTextView().setText(String.valueOf(value.getValue()));

            }
        }

    }

    public TextView getDetailTextView() {
        return mDetailTextView;
    }

    /**
     * Get a item defined as resource ID from an android style
     */
    private int getStyleItemResourceId(final Context context, final int styleId, final int attributeId, final int defValue)
    {
        // attribute to retrieve
        int[] attrs = {attributeId}; // {android.R.attr.textColor, android.R.attr.text};

        // Parse the style, using Context.obtainStyledAttributes()
        TypedArray ta = context.obtainStyledAttributes(styleId, attrs);

        // Fetch the resource, color or text defined in your style
        int styleItem = ta.getResourceId(0, defValue); // android.R.attr.textAppearanceMediumInverse);
        //int textColor = ta.getColor(1, Color.BLACK);
        //String text = ta.getString(2);

        // Recycle the TypedArray
        ta.recycle();

        return styleItem;
    }

    /**
     * Get a item defined as dimension from an android style
     */
    private float getStyleItemDimension(final Context context, final int styleId, final int attributeId, final float defValue)
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
}
