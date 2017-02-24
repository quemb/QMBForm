package com.quemb.qmbform.view;

import com.quemb.qmbform.BuildConfig;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.inputmethodservice.Keyboard;

import static org.junit.Assert.*;

/**
 * Created by Toni on 25.10.15.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FormBaseCellTest {

    FormBaseCell cell;

    @Before
    public void setUp() throws Exception {

        Activity activity = Robolectric.buildActivity(Activity.class).create().get();
        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("test");
        RowDescriptor rowDescriptor =  RowDescriptor.newInstance("sample");
        sectionDescriptor.addRow(rowDescriptor);
        cell = new FormBaseCell(activity,rowDescriptor) {
            @Override
            protected int getResource() {
                return 0;
            }

            @Override
            protected void update() {

            }
        };

    }

    @Test
    public void testShouldCreateMultiValueWrapper() {


        assertNotNull(cell.createMultiValueWrapper());


    }
}