package com.quemb.qmbform.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.quemb.qmbform.R;
import com.quemb.qmbform.adapter.FormOptionsObjectAdapter;
import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.exceptions.NoSelectorOptionsException;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormSelectorSpinnerFieldCell extends FormTitleFieldCell {
    private Spinner mSpinner;
    private ArrayList<Object> mValues;

    public FormSelectorSpinnerFieldCell(Context context,
                                        RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();
        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    @Override
    protected int getResource() {
        return R.layout.selector_spinner_field_cell;
    }

    @Override
    protected void update() {
        super.update();

        ArrayList<FormOptionsObject> selectorOptions = getRowDescriptor().getSelectorOptions();

        if (selectorOptions == null || selectorOptions.size() <= 0) {
            if(isEnabled()) {
                throw new NoSelectorOptionsException();
            }
        } else {
            FormOptionsObjectAdapter adapter = new FormOptionsObjectAdapter(getContext(),
                                                                               android.R.layout.simple_spinner_item,
                                                                               android.R.layout.simple_spinner_dropdown_item,
                                                                               selectorOptions);
            mSpinner.setAdapter(adapter);
            Object value = getRowDescriptor().getValueData();
            if (value != null) {
                mSpinner.setSelection(FormOptionsObject.indexOfFormOptionsObjectFromArrayWithValue(value, selectorOptions));
            } else {
                mSpinner.setSelection(-1);
            }

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position,
                                           long id) {
                    FormOptionsObject selectedOption = (FormOptionsObject) mSpinner.getAdapter().getItem(position);
                    onValueChanged(new Value(selectedOption.getValue()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }
}
