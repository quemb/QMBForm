package com.quemb.qmbform.annotation.validators;


import com.quemb.qmbform.R;
import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;

/**
 * Created by pmaccamp on 8/26/2015.
 */
public class PositiveNumberValidator implements FormValidator {
    @Override
    public RowValidationError validate(RowDescriptor descriptor) {
        Object valueData = descriptor.getValue().getValue();
        if (valueData != null) {
            if (valueData instanceof Number && ((Number) valueData).doubleValue() > 0) {
                return null;
            } else if (valueData instanceof Double && ((Double) valueData) > 0) {
                return null;
            } else if (valueData instanceof Float && ((Float) valueData) > 0) {
                return null;
            } else if (valueData instanceof Integer && ((Integer) valueData) > 0) {
                return null;
            }
        }
        return new RowValidationError(descriptor, R.string.nonpositive_number_error);
    }
}
