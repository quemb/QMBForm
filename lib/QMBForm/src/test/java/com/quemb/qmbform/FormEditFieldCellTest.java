package com.quemb.qmbform;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.view.FormEditIntegerFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tonimoeckel on 02.09.14.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FormEditFieldCellTest {


    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldBeDisabled(){

        RowDescriptor rowDescriptor = RowDescriptor.newInstance("disabled", RowDescriptor.FormRowDescriptorTypeInteger);
        rowDescriptor.setDisabled(true);

        FormEditIntegerFieldCell testCell = new FormEditIntegerFieldCell(activity, rowDescriptor);

        assertThat(testCell.getEditText().isEnabled(), is(false));

    }

    @After
    public void tearDown() {

    }

}
