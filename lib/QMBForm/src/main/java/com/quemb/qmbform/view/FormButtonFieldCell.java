package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.utils.Styling;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormButtonFieldCell extends FormTitleFieldCell {


    public FormButtonFieldCell(Context context,
                               RowDescriptor<?> rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected int getResource() {
        return R.layout.button_field_cell;
    }

    @Override
    protected void update() {
        super.update();

        TextView textView = getTextView();
        setStyleId(getTextView(), CellDescriptor.APPEARANCE_BUTTON, CellDescriptor.COLOR_VALUE);

        if (getRowDescriptor().getDisabled())
        {
            if (setTextColor(textView, CellDescriptor.COLOR_VALUE_DISABLED) == false)
                textView.setTextColor(Styling.getColorFromAttr(textView.getContext(), android.R.attr.textColor));

            textView.setClickable(false);
            textView.setEnabled(false);

            setClickable(false);
            setEnabled(false);
        }
    }

    @Override
    public void onCellSelected() {
        super.onCellSelected();


    }
}
