package com.quemb.qmbform.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.quemb.qmbform.R;
import com.quemb.qmbform.adapter.FormOptionsObjectAdapter;
import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormSpinnerFieldCell extends FormTitleFieldCell {
    private Spinner mSpinner;
    private ArrayList<Object> mValues;

    public FormSpinnerFieldCell(Context context,
                                RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();
        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    @Override
    protected int getResource() {
        return R.layout.spinner_field_cell;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void update() {
        super.update();

        List<FormOptionsObject> selectorOptions = getRowDescriptor().getSelectorOptions();

        if (selectorOptions.size() > 0) {
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
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.title_no_entries);
            builder.setMessage(R.string.msg_no_entries);
            AlertDialog dialog = builder.create();
            dialog.show();
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

    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }
}
