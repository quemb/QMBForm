package com.quemb.qmbform.annotation;

import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;

/**
 * Created by pmaccamp on 8/26/2015.
 */
public interface FormValidator {
    /**
     * @return {@link RowValidationError} if there is an error else null
     */
    public RowValidationError validate(RowDescriptor descriptor);
}
