package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.OnValueChangeListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public abstract class FormBaseCell extends Cell  {


    private static final int REMOVE_BUTTON_ID = R.id.end;
    private static final int ADD_BUTTON_ID = R.id.beginning;


    private LinearLayout mMultiValueWrapper;

    public FormBaseCell(Context context, RowDescriptor rowDescriptor) {

        super(context, rowDescriptor);

    }

    @Override
    protected void init() {
        super.init();

        if (getRowDescriptor() != null && getRowDescriptor().getValue() != null) {
            getRowDescriptor().getValue().setOnValueChangeListener(new OnValueChangeListener() {
                @Override
                public void onChange(Object value) {
                    update();
                }
            });
        }



    }

    protected ViewGroup getSuperViewForLayoutInflation(){

        if (getRowDescriptor().getSectionDescriptor() != null && this.getRowDescriptor().getSectionDescriptor().isMultivalueSection()){
            LinearLayout linearLayout = createMultiValueWrapper();
            addView(linearLayout);
            return linearLayout;
        }
        return super.getSuperViewForLayoutInflation();
    }

    private LinearLayout createMultiValueWrapper() {

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setId(R.id.wrap_content);
        linearLayout.setFocusable(false);
        linearLayout.setFocusableInTouchMode(false);

        ImageButton deleteButton = new ImageButton(getContext());
        deleteButton.setId(REMOVE_BUTTON_ID);
        deleteButton.setFocusableInTouchMode(false);
        deleteButton.setFocusable(false);

        Drawable removeIcon = AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_action_remove);
        removeIcon.setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);

        deleteButton.setImageDrawable(removeIcon);
        deleteButton.setBackgroundColor(Color.TRANSPARENT);
        deleteButton.setVisibility(VISIBLE);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SectionDescriptor sectionDescriptor = getRowDescriptor().getSectionDescriptor();
                sectionDescriptor.removeRow(getRowDescriptor());

            }
        });
        linearLayout.addView(deleteButton);

        ImageButton addButton = new ImageButton(getContext());
        addButton.setId(ADD_BUTTON_ID);
        addButton.setFocusableInTouchMode(false);
        addButton.setFocusable(false);

        Drawable addIcon = AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_action_new);
        addIcon.setColorFilter(0xff00ff00, PorterDuff.Mode.MULTIPLY);


        addButton.setImageDrawable(addIcon);
        addButton.setBackgroundColor(Color.TRANSPARENT);
        addButton.setVisibility(GONE);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SectionDescriptor sectionDescriptor = getRowDescriptor().getSectionDescriptor();
                sectionDescriptor.addRow(RowDescriptor.newInstance(getRowDescriptor()));

            }
        });
        linearLayout.addView(addButton);

        SectionDescriptor sectionDescriptor =getRowDescriptor().getSectionDescriptor();
        int index = sectionDescriptor.getIndexOfRowDescriptor(getRowDescriptor());
        if (index == sectionDescriptor.getRowCount()-1){
            addButton.setVisibility(VISIBLE);
            deleteButton.setVisibility(GONE);
        }else {
            addButton.setVisibility(GONE);
            deleteButton.setVisibility(VISIBLE);
        }

        mMultiValueWrapper = linearLayout;

        return mMultiValueWrapper;
    }

    @Override
    public boolean shouldAddDivider() {

        RowDescriptor rowDescriptor = (RowDescriptor) getFormItemDescriptor();
        if (rowDescriptor.isLastRowInSection())
            return false;

        return super.shouldAddDivider();
    }

    @Override
    public void lastInSection() {


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
