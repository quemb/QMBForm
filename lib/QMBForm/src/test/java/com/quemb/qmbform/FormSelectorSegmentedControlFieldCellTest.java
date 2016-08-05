package com.quemb.qmbform;

import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by pmaccamp on 9/14/2015.
 */
@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FormSelectorSegmentedControlFieldCellTest {

    @Before
    public void setUp() {
    }

    @Test
    public void hasCorrectSegmentSelected() {
        RowDescriptor<String> rowDescriptor = RowDescriptor.newInstance("segmented",
                RowDescriptor.FormRowDescriptorTypeSelectorSegmentedControl,
                "Segmented Control Test",
                new Value<String>("1"));

        ArrayList<FormOptionsObject> selectorOptions = new ArrayList<FormOptionsObject>();
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("0", "Test 0"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("1", "Test 1"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("2", "Test 2"));

        rowDescriptor.setSelectorOptions(selectorOptions);

        assertThat(rowDescriptor, is(notNullValue()));
        // Check that value exists in selector options
        FormOptionsObject selected = FormOptionsObject.formOptionsObjectFromArrayWithValue(
                rowDescriptor.getValueData(), selectorOptions);

        assertThat(selected.getDisplayText(),
                is("Test 1"));

    }

    @After
    public void tearDown() {

    }

}
