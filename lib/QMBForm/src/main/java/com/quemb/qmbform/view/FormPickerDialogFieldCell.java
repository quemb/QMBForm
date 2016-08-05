package com.quemb.qmbform.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.exceptions.NoDataSourceException;

import java.util.List;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormPickerDialogFieldCell extends FormDetailTextInlineFieldCell {


    public FormPickerDialogFieldCell(Context context,
                                     RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void update() {

        super.update();
        setEnabled(!getRowDescriptor().getDisabled());

    }

    @Override
    public void onCellSelected() {

        super.onCellSelected();
        if (getRowDescriptor().getDataSource() == null) {
            throw new NoDataSourceException();
        } else {
            loadData(getRowDescriptor());
        }

    }

    // Capture the wildcard of RowDescriptor
    private <T> void loadData(RowDescriptor<T> rowDescriptor)
    {
        rowDescriptor.getDataSource().loadData(new DataSourceListener()
        {
            @Override
            public <T> void onDataSourceLoaded(List<T> list)
            {
                if (list.size() > 0)
                {
                    final ArrayAdapter adapter = new ArrayAdapter<T>(getContext(), android.R.layout.simple_selectable_list_item, list);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            onValueChanged(new Value<Object>(adapter.getItem(which)));
                            update();
                            dialog.dismiss();
                        }
                    })
                            .setTitle(getRowDescriptor().getTitle());

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
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
