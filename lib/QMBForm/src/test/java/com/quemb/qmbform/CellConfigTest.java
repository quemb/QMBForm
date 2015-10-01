package com.quemb.qmbform;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.TextView;

import com.quemb.qmbform.descriptor.CellConfigObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.view.FormDetailTextFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pmaccamp on 9/24/2015.
 */

@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class CellConfigTest {
    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void canAddCellConfigs() {
        RowDescriptor rowDescriptor = RowDescriptor.newInstance("valid",
                RowDescriptor.FormRowDescriptorTypeDetail,
                "Test",
                new Value<String>("Test"));

        HashMap<String, CellConfigObject[]> cellConfig = new HashMap<String, CellConfigObject[]>();
        int[] padding = {12, 13, 14, 15};
        CellConfigObject[] config = {
                new CellConfigObject(CellConfigObject.CONFIG_TYPE.PADDING, padding),
                new CellConfigObject(CellConfigObject.CONFIG_TYPE.TYPEFACE, Typeface.BOLD_ITALIC),
                new CellConfigObject(CellConfigObject.CONFIG_TYPE.INPUT_TYPE, InputType.TYPE_NUMBER_FLAG_DECIMAL),
                new CellConfigObject(CellConfigObject.CONFIG_TYPE.GRAVITY, Gravity.CENTER),
        };
        cellConfig.put("getLabelTextView", config);
        cellConfig.put("getDetailTextView", config);
        rowDescriptor.setCellConfig(cellConfig);

        FormDetailTextFieldCell detailTextFieldCell = new FormDetailTextFieldCell(activity, rowDescriptor);

        TextView view = detailTextFieldCell.getLabelTextView();
        assertThat(view.getPaddingLeft(), is(padding[0]));
        assertThat(view.getPaddingTop(), is(padding[1]));
        assertThat(view.getPaddingRight(), is(padding[2]));
        assertThat(view.getPaddingBottom(), is(padding[3]));
        assertThat(view.getTypeface().getStyle(), is(Typeface.BOLD_ITALIC));
        assertThat(view.getInputType(), is(InputType.TYPE_NUMBER_FLAG_DECIMAL));
        assertThat(view.getGravity(), is(Gravity.CENTER));

        view = detailTextFieldCell.getDetailTextView();
        assertThat(view.getPaddingLeft(), is(padding[0]));
        assertThat(view.getPaddingTop(), is(padding[1]));
        assertThat(view.getPaddingRight(), is(padding[2]));
        assertThat(view.getPaddingBottom(), is(padding[3]));
        assertThat(view.getTypeface().getStyle(), is(Typeface.BOLD_ITALIC));
        assertThat(view.getInputType(), is(InputType.TYPE_NUMBER_FLAG_DECIMAL));
        assertThat(view.getGravity(), is(Gravity.CENTER));
    }

    @After
    public void tearDown() {

    }

}
