package com.quemb.qmbform.annotation;

import com.quemb.qmbform.descriptor.RowDescriptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tonimoeckel on 06.02.14.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormElement {


    public int label() default android.R.string.untitled;
    public String rowDescriptorType() default RowDescriptor.FormRowDescriptorTypeName;
    public String tag() default "";
    public int hint() default android.R.string.untitled;
    public int sortId() default 100;
    public boolean required() default false;
    public String dateFormat() default "yyyy-MM-dd HH:mm:ss";
    public int section() default android.R.string.untitled;
    public boolean disabled() default false;
}
