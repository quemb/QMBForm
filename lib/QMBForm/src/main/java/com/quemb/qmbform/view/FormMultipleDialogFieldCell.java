package com.quemb.qmbform.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.exceptions.NoDataSourceException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nico Ziegler on 05.01.15.
 */
public class FormMultipleDialogFieldCell extends FormDetailTextVerticalFieldCell {

    public FormMultipleDialogFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    public void onCellSelected() {
        super.onCellSelected();

        if (getRowDescriptor().getDataSource() == null) {
            throw new NoDataSourceException();
        } else {
            getRowDescriptor().getDataSource().loadData(new DataSourceListener() {
                @Override
                public void onDataSourceLoaded(ArrayList list) {

                    final ArrayList<String> selectedItems = new ArrayList((ArrayList<String>) getRowDescriptor().getValue().getValue());

                    if (list.size() > 0) {
                        final ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_selectable_list_item, list);
                        final CharSequence[] charSequence = (CharSequence[]) list.toArray(new CharSequence[list.size()]);
                        final boolean[] checkedItems = new boolean[list.size()];

                        for (int i = 0; i < list.size(); i++) {
                            checkedItems[i] = selectedItems.contains(list.get(i));
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getRowDescriptor().getTitle());
                        builder.setMultiChoiceItems(charSequence, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    selectedItems.add(adapter.getItem(which).toString());
                                } else {
                                    selectedItems.remove(adapter.getItem(which).toString());
                                }
                            }
                        });
                        builder.setPositiveButton(R.string.btn_select, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onValueChanged(new Value<ArrayList>(selectedItems));
                                update();
                                dialog.dismiss();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(R.string.title_no_entries);
                        builder.setMessage(R.string.msg_no_entries);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        }

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
            } else if (value.getValue() instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) value.getValue();
                String stringValue = "";
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i > 0) {
                        stringValue = stringValue.concat(", ");
                    }
                    stringValue = stringValue.concat((String) arrayList.get(i));
                }
                getDetailTextView().setText(stringValue);
            } else {
                getDetailTextView().setText(String.valueOf(value.getValue()));

            }
        }
    }
}
