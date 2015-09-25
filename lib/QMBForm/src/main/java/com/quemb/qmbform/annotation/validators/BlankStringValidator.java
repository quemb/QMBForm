package com.quemb.qmbform.annotation.validators;


import com.quemb.qmbform.R;
import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

/**
 * Created by pmaccamp on 8/26/2015.
 */
public class BlankStringValidator implements FormValidator {
    @Override
    public RowValidationError validate(RowDescriptor descriptor) {
        Value value = descriptor.getValue();
        if (value.getValue() != null &&
            value.getValue() instanceof String) {
            String str = (String) value.getValue();

            // if a valid string return null
            if (str.replace(" ", "").length() > 0) {
                return null;
            }
        }
        return new RowValidationError(descriptor, R.string.validation_is_required);
    }
}
