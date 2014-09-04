package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditTextViewFieldCell extends FormEditTextFieldCell {

    private TextView mTextView;
    private EditText mEditView;

    public FormEditTextViewFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }


    @Override
    protected void init() {
        super.init();

        EditText editView = getEditView();
//        mEditView.setRawInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_view_field_cell;
    }
}
