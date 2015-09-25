package com.quemb.qmbform.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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
public class FormSelectorPickerDialogInlineFieldCell extends FormDetailTextInlineFieldCell {


    public FormSelectorPickerDialogInlineFieldCell(Context context,
                                                   RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void update() {
        updateTitle();

        if (getRowDescriptor().getHint(getContext()) != null) {
            getDetailTextView().setHint(getRowDescriptor().getHint(getContext()));
        }

        Value<?> value = getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            FormOptionsObject optionsObject =
                FormOptionsObject.formOptionsObjectFromArrayWithValue(value.getValue(), getRowDescriptor().getSelectorOptions());
            if (optionsObject != null) {
                getDetailTextView().setText(optionsObject.getDisplayText());
            }
        }

    }

    @Override
    protected int getResource() {
        return R.layout.selector_picker_dialog_inline_field_cell;
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();

        ArrayList<FormOptionsObject> selectorOptions = getRowDescriptor().getSelectorOptions();

        if (selectorOptions == null || selectorOptions.size() <= 0) {
            if(isEnabled()) {
                throw new NoSelectorOptionsException();
            }
        } else {
            final FormOptionsObjectAdapter adapter = new FormOptionsObjectAdapter(getContext(),
                                                                                     android.R.layout.simple_spinner_item,
                                                                                     android.R.layout.simple_spinner_dropdown_item,
                                                                                     selectorOptions);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setSingleChoiceItems(adapter,
                                            FormOptionsObject.indexOfFormOptionsObjectFromArrayWithValue(getRowDescriptor().getValueData(),
                                                                                                            selectorOptions),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    FormOptionsObject selectedOption = (FormOptionsObject) adapter.getItem(which);
                                                    onValueChanged(new Value<>(selectedOption.getValue()));
                                                    update();
                                                    dialog.dismiss();
                                                }
                                            })
                .setTitle(getRowDescriptor().getTitle());

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
