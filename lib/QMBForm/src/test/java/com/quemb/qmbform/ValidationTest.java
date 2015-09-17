package com.quemb.qmbform;

import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.annotation.validators.EmailValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pmaccamp on 9/14/2015.
 */

@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class ValidationTest {
    @Before
    public void setUp() {

    }

    @Test
    public void isValidRow() {
        RowDescriptor rowDescriptor = RowDescriptor.newInstance("valid",
                RowDescriptor.FormRowDescriptorTypeEmail,
                "Email Test",
                new Value<String>("test@yahoo.com"));

        rowDescriptor.addValidator(new EmailValidator());

        //Add true dummy validator to test multiple validation
        rowDescriptor.addValidator(new FormValidator() {
            @Override
            public RowValidationError validate(RowDescriptor descriptor) {
                return descriptor.getValueData().equals("test@yahoo.com") ?
                        null : new RowValidationError(descriptor, R.string.test_error);
            }
        });

        assertThat(rowDescriptor.isValid(), is(true));
    }

    @Test
    public void isInvalidRow() {
        RowDescriptor rowDescriptor = RowDescriptor.newInstance("valid",
                RowDescriptor.FormRowDescriptorTypeEmail,
                "Email Test",
                new Value<String>("notavalidemail"));

        //Add true dummy validator to test multiple validation
        rowDescriptor.addValidator(new FormValidator() {
            @Override
            public RowValidationError validate(RowDescriptor descriptor) {
                return descriptor.getValueData().equals("notavalidemail") ?
                        null : new RowValidationError(descriptor, R.string.test_error);
            }
        });

        rowDescriptor.addValidator(new EmailValidator());

        assertThat(rowDescriptor.isValid(), is(false));
    }

    @After
    public void tearDown() {

    }

}
