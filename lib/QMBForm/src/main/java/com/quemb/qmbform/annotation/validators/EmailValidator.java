package com.quemb.qmbform.annotation.validators;


import com.quemb.qmbform.R;
import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by pmaccamp on 8/26/2015.
 */
public class EmailValidator implements FormValidator {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public RowValidationError validate(RowDescriptor descriptor) {
        Value value = descriptor.getValue();
        if (value.getValue() != null && value.getValue() instanceof String) {
            String val = (String) value.getValue();
            return (val.matches(EMAIL_PATTERN)) ? null : new RowValidationError(descriptor, R.string.validation_invalid_email);
        }
        return new RowValidationError(descriptor, R.string.validation_invalid_email);
    }
}
