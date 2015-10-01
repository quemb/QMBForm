package com.quemb.qmbform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pmaccamp on 9/8/2015.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormOptionsObjectElement {
    public String value() default "";

    public ValueTypes valueType() default ValueTypes.STRING;

    public String displayText();

    enum ValueTypes {
        INT, DOUBLE, STRING
    }
}
