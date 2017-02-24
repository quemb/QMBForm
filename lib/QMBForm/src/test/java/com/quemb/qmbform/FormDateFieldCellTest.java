package com.quemb.qmbform;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.view.FormTimeDialogFieldCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tonimoeckel on 02.09.14.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FormDateFieldCellTest {


    private Activity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).create().get();
    }

    @Test
    public void shouldBeDisabled(){
        RowDescriptor rowDescriptor = RowDescriptor.newInstance("timeDialog",RowDescriptor.FormRowDescriptorTypeTime, "Time Dialog", new Value<Date>(new Date()));
        rowDescriptor.setDisabled(true);

        FormTimeDialogFieldCell testCell = new FormTimeDialogFieldCell(activity, rowDescriptor);

        assertThat(testCell.getTextView().isEnabled(), is(false));

    }

    @After
    public void tearDown() {

    }

}
