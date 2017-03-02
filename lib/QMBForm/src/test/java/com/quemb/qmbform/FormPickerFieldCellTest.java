package com.quemb.qmbform;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.view.FormPickerDialogFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tonimoeckel on 02.09.14.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class FormPickerFieldCellTest {


    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldBeDisabled(){

        RowDescriptor rowDescriptor = RowDescriptor.newInstance("picker",RowDescriptor.FormRowDescriptorTypeButton, "Tap Me");
        rowDescriptor.setDisabled(true);

        FormPickerDialogFieldCell testCell = new FormPickerDialogFieldCell(activity, rowDescriptor);

        assertThat( testCell.getTextView().isClickable(), is(false));

    }

    @After
    public void tearDown() {

    }

}
