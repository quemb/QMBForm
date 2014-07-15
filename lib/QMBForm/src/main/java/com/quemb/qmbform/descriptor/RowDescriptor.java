package com.quemb.qmbform.descriptor;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class RowDescriptor extends FormItemDescriptor{

    public static String FormRowDescriptorTypeText = "text";
    public static String FormRowDescriptorTypeName = "name";
    public static String FormRowDescriptorTypeURL = "url";
    public static String FormRowDescriptorTypeEmail = "email";
    public static String FormRowDescriptorTypePassword = "password";
    public static String FormRowDescriptorTypeNumber = "number";
    public static String FormRowDescriptorTypePhone = "phone";
    public static String FormRowDescriptorTypeTwitter = "twitter";
    public static String FormRowDescriptorTypeAccount = "account";
    public static String FormRowDescriptorTypeInteger = "integer";
    public static String FormRowDescriptorTypeTextView = "textView";
    public static String FormRowDescriptorTypeSelectorPush = "selectorPush";
    public static String FormRowDescriptorTypeSelectorActionSheet = "selectorActionSheet";
    public static String FormRowDescriptorTypeSelectorAlertView = "selectorAlertView";
    public static String FormRowDescriptorTypeSelectorPickerView = "selectorPickerView";
    public static String FormRowDescriptorTypeSelectorPickerViewInline = "selectorPickerViewInline";
    public static String FormRowDescriptorTypeMultipleSelector = "multipleSelector";
    public static String FormRowDescriptorTypeSelectorLeftRight = "selectorLeftRight";
    public static String FormRowDescriptorTypeSelectorSegmentedControl = "selectorSegmentedControl";
    public static String FormRowDescriptorTypeDateInline = "dateInline";
    public static String FormRowDescriptorTypeDateTimeInline = "datetimeInline";
    public static String FormRowDescriptorTypeTimeInline = "timeInline";
    public static String FormRowDescriptorTypeDate = "date";
    public static String FormRowDescriptorTypeDateTime = "datetime";
    public static String FormRowDescriptorTypeTime = "time";
    public static String FormRowDescriptorTypeDatePicker = "datePicker";
    public static String FormRowDescriptorTypePicker = "picker";
    public static String FormRowDescriptorTypeBooleanCheck = "booleanCheck";
    public static String FormRowDescriptorTypeBooleanSwitch = "booleanSwitch";
    public static String FormRowDescriptorTypeButton = "button";
    public static String FormRowDescriptorTypeImage = "image";
    public static String FormRowDescriptorTypeStepCounter = "stepCounter";

    private String mRowType;
    private Value<?> mValue;
    private Boolean mRequired = false;

    private SectionDescriptor mSectionDescriptor;

    public static RowDescriptor newInstance(String tag){

        return RowDescriptor.newInstance(tag, FormRowDescriptorTypeName);

    }

    public static RowDescriptor newInstance(String tag, String rowType){

        return RowDescriptor.newInstance(tag, rowType,null);

    }

    public static RowDescriptor newInstance(String tag, String rowType, String title){

        return RowDescriptor.newInstance(tag, rowType, title, null);

    }

    public static RowDescriptor newInstance(String tag, String rowType, String title, Value<?> value){

        RowDescriptor descriptor = new RowDescriptor();
        descriptor.mTitle = title;
        descriptor.mTag = tag;
        descriptor.mRowType = rowType;
        descriptor.setValue(value);

        return descriptor;

    }

    public SectionDescriptor getSectionDescriptor() {
        return mSectionDescriptor;
    }

    public void setSectionDescriptor(SectionDescriptor sectionDescriptor) {
        mSectionDescriptor = sectionDescriptor;
    }

    public Value<?> getValue() {
        return mValue;
    }

    public void setValue(Value<?> value) {
        mValue = value;
    }

    public Boolean getRequired() {
        return mRequired;
    }

    public void setRequired(Boolean required) {
        mRequired = required;
    }

    public String getRowType() {
        return mRowType;
    }
}
