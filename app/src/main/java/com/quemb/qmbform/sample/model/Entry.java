package com.quemb.qmbform.sample.model;

import com.quemb.qmbform.annotation.FormElement;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.sample.R;

import java.util.Date;

/**
 * Created by tonimoeckel on 30.07.14.
 */
public class Entry {

    @FormElement(
            label = R.string.lb_title,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeText,
            sortId = 1,
            section = R.string.section_general
    )
    public String title;

    @FormElement(
            label = R.string.lb_description,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeTextView,
            sortId = 2,
            section = R.string.section_general
    )
    public String description;

    @FormElement(
            label = R.string.lb_date_dialog,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeDate,
            sortId = 4,
            section = R.string.section_date
    )
    public Date date;

    @FormElement(
            label = R.string.lb_date_inline,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeDateInline,
            tag = "customDateInlineTag",
            sortId = 3,
            section = R.string.section_date
    )
    public Date dateInline;

    @FormElement(
            label = R.string.lb_boolean_check,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeBooleanSwitch,
            disabled = true,
            tag = "checkSwitch",
            sortId = 5,
            section = R.string.section_boolean
    )
    public Boolean checkSwitch;

}
