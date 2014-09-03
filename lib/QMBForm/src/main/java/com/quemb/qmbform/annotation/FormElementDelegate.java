package com.quemb.qmbform.annotation;

import com.quemb.qmbform.descriptor.RowDescriptor;

import java.lang.reflect.Field;

/**
 * Created by tonimoeckel on 03.09.14.
 */
public interface FormElementDelegate {

    public boolean shouldAddRowDescriptorForField(RowDescriptor rowDescriptor, Field field);

}
