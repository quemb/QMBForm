package com.quemb.qmbform.descriptor;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class RowDescriptor<T> extends FormItemDescriptor{

    public static final String FormRowDescriptorTypeText = "text";
    public static final String FormRowDescriptorTypeName = "name";
    public static final String FormRowDescriptorTypeURL = "url";
    public static final String FormRowDescriptorTypeEmail = "email";
    public static final String FormRowDescriptorTypePassword = "password";
    public static final String FormRowDescriptorTypeNumber = "number";
    public static final String FormRowDescriptorTypeIntegerSlider = "integerSlider";
    public static final String FormRowDescriptorTypeCurrency = "currency";
    public static final String FormRowDescriptorTypePhone = "phone";
    public static final String FormRowDescriptorTypeTwitter = "twitter";
    public static final String FormRowDescriptorTypeAccount = "account";
    public static final String FormRowDescriptorTypeInteger = "integer";
    public static final String FormRowDescriptorTypeTextView = "textView";
    public static final String FormRowDescriptorTypeSelectorPush = "selectorPush";
    public static final String FormRowDescriptorTypeSelectorActionSheet = "selectorActionSheet";
    public static final String FormRowDescriptorTypeSelectorAlertView = "selectorAlertView";
    public static final String FormRowDescriptorTypeSelectorPickerView = "selectorPickerView";
    public static final String FormRowDescriptorTypeSelectorPickerViewInline = "selectorPickerViewInline";
    public static final String FormRowDescriptorTypeSelectorSpinner = "selectorSpinner";
    public static final String FormRowDescriptorTypeSelectorPickerDialog = "selectorPickerDialog";
    public static final String FormRowDescriptorTypeMultipleSelector = "multipleSelector";
    public static final String FormRowDescriptorTypeSelectorLeftRight = "selectorLeftRight";
    public static final String FormRowDescriptorTypeSelectorSegmentedControl = "selectorSegmentedControl";
    public static final String FormRowDescriptorTypeDateInline = "dateInline";
    public static final String FormRowDescriptorTypeDateTimeInline = "datetimeInline";
    public static final String FormRowDescriptorTypeTimeInline = "timeInline";
    public static final String FormRowDescriptorTypeDate = "date";
    public static final String FormRowDescriptorTypeDateTime = "datetime";
    public static final String FormRowDescriptorTypeTime = "time";
    public static final String FormRowDescriptorTypeDatePicker = "datePicker";
    public static final String FormRowDescriptorTypePicker = "picker";
    public static final String FormRowDescriptorTypeBooleanCheck = "booleanCheck";
    public static final String FormRowDescriptorTypeBooleanSwitch = "booleanSwitch";
    public static final String FormRowDescriptorTypeButton = "button";
    public static final String FormRowDescriptorTypeImage = "image";
    public static final String FormRowDescriptorTypeStepCounter = "stepCounter";

    private String mRowType;
    private Value<T> mValue;
    /**
     * A list of valid values to pick from (e.g. used for spinners)
     */
    private DataSource<T> mDataSource;
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

    public Value<T> getValue() {
        return mValue;
    }

    public void setValue(Value<T> value) {
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

    public boolean hasDataSource(){
        return mDataSource != null;
    }

    public DataSource<T> getDataSource() {
        return mDataSource;
    }

    public void setDataSource(DataSource<T> dataSource) {
        mDataSource = dataSource;
    }
}
