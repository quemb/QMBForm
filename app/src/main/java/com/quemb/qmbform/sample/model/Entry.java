package com.quemb.qmbform.sample.model;

import com.quemb.qmbform.annotation.FormElement;
import com.quemb.qmbform.annotation.FormElementDelegate;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.sample.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tonimoeckel on 30.07.14.
 */
public class Entry implements FormElementDelegate {

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

    @FormElement(
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeText,
            sortId = 6,
            section = R.string.section_multiValue,
            hint = R.string.lb_add_new_tag,
            multiValue = true
    )
    public ArrayList<String> multiValue;

    /**
     * Use this interface method to decide at runtime if the generate rowDescriptor should be added to the form descriptor
     * Also use this as a hook, to manual set RowDescriptor properties
     *
     * @param rowDescriptor
     * @param field
     * @return
     */
    @Override
    public boolean shouldAddRowDescriptorForField(RowDescriptor rowDescriptor, Field field) {

        // For some reason you don't want "description" to be added
        if (field.getName().equals("description")){
            return false;
        }

        return true;

    }
}
