package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditTextFieldCell extends FormBaseCell {

    private TextView mTextView;
    private EditText mEditView;

    public FormEditTextFieldCell(Context context,
            RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mTextView = (TextView)findViewById(R.id.textView);
        mEditView = (EditText)findViewById(R.id.editText);
        mEditView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                onValueChanged(new Value<String>(s.toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_field_cell;
    }

    @Override
    protected void update() {

        String title = getFormItemDescriptor().getTitle();
        mTextView.setText(title);
        mTextView.setVisibility(title == null?GONE:VISIBLE);

        Value<String> value = (Value<String>) getRowDescriptor().getValue();
        if (value != null){
            String valueString = value.getValue();
            mEditView.setText(valueString);
        }

    }
}
