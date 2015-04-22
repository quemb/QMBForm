package com.quemb.qmbform.view;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.SeekBar;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 25.08.14.
 */
public class FormIntegerSliderFieldCell extends FormDetailTextFieldCell {

    private SeekBar mSeekBar;
    public final static String CellConfigMaxKey = "CellConfigMaxKey";

    public FormIntegerSliderFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {

        super.init();
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                getDetailTextView().setText(Integer.toString(progress));
                onValueChanged(new Value<Integer>(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected int getResource() {
        return R.layout.integer_slider_field_cell;
    }

    @Override
    protected void update() {

        super.update();

        Value<Integer> value = (Value<Integer>) getRowDescriptor().getValue();

        HashMap<String, Object> config = getRowDescriptor().getCellConfig();
        Integer max = config != null && config.containsKey(CellConfigMaxKey) ? (Integer) config.get(CellConfigMaxKey) : 100;

        mSeekBar.setMax(max);
        mSeekBar.setProgress(value.getValue());
        mSeekBar.setEnabled(!getRowDescriptor().getDisabled());

    }
    public SeekBar getSeekBar(){
        return mSeekBar;
    }
}
