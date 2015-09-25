package com.quemb.qmbform.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by pmaccamp on 9/8/2015.
 */
public class FormSelectorSegmentedControlFieldCell extends FormBaseCell {
    private SegmentedGroup mSegmentedGroup;
    private TextView mLabelTextView;
    private ArrayList<Object> mValues;

    public FormSelectorSegmentedControlFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();
        mValues = new ArrayList<Object>();
        mSegmentedGroup = (SegmentedGroup) findViewById(R.id.segmentedGroup);
        mLabelTextView = (TextView) findViewById(R.id.labelTextView);
        mLabelTextView.setTextColor(R.attr.editTextColor);
        mLabelTextView.setTextAppearance(getContext(), R.style.Base_TextAppearance_AppCompat_Body2);
        mLabelTextView.setTypeface(mLabelTextView.getTypeface(), Typeface.BOLD);
    }

    @Override
    protected int getResource() {
        return R.layout.selector_segmented_control_field_cell;
    }

    @Override
    protected void update() {
        mSegmentedGroup.removeAllViews();

        mLabelTextView.setText(getFormItemDescriptor().getTitle());
        ArrayList<FormOptionsObject> selectorOptions = getRowDescriptor().getSelectorOptions();
        for (FormOptionsObject option : selectorOptions) {
            addButton(mSegmentedGroup,
                mValues.size(),
                option.getDisplayText(),
                option.getValue().equals(getRowDescriptor().getValueData()));
            mValues.add(option.getValue());
        }

        mSegmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId >= 0 && checkedId < mValues.size()) {
                    onValueChanged(new Value<>(mValues.get(checkedId)));
                }
            }
        });
    }

    private void addButton(SegmentedGroup group, int id, String displayText, boolean checked) {
        RadioButton radioButton = new RadioButton(getContext(), null, R.style.RadioButton);
        radioButton.setLayoutParams(new SegmentedGroup.LayoutParams(0,
            ViewGroup.LayoutParams.MATCH_PARENT,
            1));
        radioButton.setText(displayText);
        radioButton.setId(id);
        radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
        radioButton.setClickable(true);
        radioButton.setChecked(checked);
        int padding = getResources().getDimensionPixelOffset(R.dimen.cell_padding);
        radioButton.setPadding(padding, padding, padding, padding);
        group.addView(radioButton);
        group.updateBackground();
    }
}