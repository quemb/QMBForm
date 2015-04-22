package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormSpinnerFieldCell extends FormTitleFieldCell {

    private Spinner mSpinner;

    public FormSpinnerFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mSpinner = (Spinner)findViewById(R.id.spinner);

    }

    @Override
    protected int getResource() {
        return R.layout.spinner_field_cell;
    }

    @Override
    protected void update() {

        super.update();

        getRowDescriptor().getDataSource().loadData(new DataSourceListener() {
            @Override
            public void onDataSourceLoaded(ArrayList list) {

                if (list.size()>0){
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
                    adapter.addAll(list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);
                    Value value = getRowDescriptor().getValue();
                    if (value != null){
                        mSpinner.setSelection(adapter.getPosition(value.getValue()));
                    }else {
                        mSpinner.setSelection(-1);
                    }
                    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long id) {
                            onValueChanged(new Value(mSpinner.getAdapter().getItem(position)));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(R.string.title_no_entries);
                    builder.setMessage(R.string.msg_no_entries);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });


    }


    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }
}
