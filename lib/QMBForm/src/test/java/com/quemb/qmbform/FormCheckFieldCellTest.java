package com.quemb.qmbform;

import android.app.Activity;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.view.FormCheckFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tonimoeckel on 02.09.14.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FormCheckFieldCellTest {


    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldBeDisabled(){

        RowDescriptor<Boolean> rowDescriptor = RowDescriptor.newInstance("disabled", RowDescriptor.FormRowDescriptorTypeBooleanCheck);
        rowDescriptor.setDisabled(true);

        FormCheckFieldCell testCell = new FormCheckFieldCell(activity, rowDescriptor);

        assertThat(testCell.getCheckBox().isEnabled(), is(false));

    }

    @After
    public void tearDown() {

    }

}
