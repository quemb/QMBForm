QMBForm
=======

Create simple Android forms

![Demo](resources/sample_cast.gif)

## Basic Usage

1.  Create a "FromDescriptor" - It's the holder for the form

    ```java
    FormDescriptor descriptor = FormDescriptor.newInstance();
    descriptor.setOnFormRowValueChangedListener(this); //Listen for changes
    ```

1.  Create sections with "SectionDescriptor" and add rows - Sections can have titles and rows (Form Elements)

    ```java
    SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("tag","Title");
    
    //Add rows - form elements
    sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeText, "Text", new Value<String>("test")) );
    sectionDescriptor.addRow( RowDescriptor.newInstance("dateDialog",RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog") );
    ```
    
1.  To render your form, use the "FormManager" helper. You will need a ListView instance.
  
    ```java
        FormManager formManager = new FormManager();
        formManager.setup(descriptor, mListView, getActivity());
        formManager.setOnFormRowClickListener(this);    
    ```


    
1.  Attention! If you use EditText based form elements, make sure you set "windowSoftInputMode" to "adjustPan"

    Do it in your manifest:
    ```xml
    <activity
			      android:windowSoftInputMode="adjustPan"/>
            
    ```

    Or programmatically in the onCreate method of your activity
    ```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        
    }
    ```
    
1.  Create elements with custom type identifier and a FormCell class. You can also submit configs to use objects in your cell

    ```java
    String customType = "customRowIdentifier";
    CellViewFactory.getInstance().setRowTypeMap(customType, CustomFormCell.class);

    RowDescriptor customRow = RowDescriptor.newInstance("custom",customType, "title", new Value<String>("value"));
    HashMap<String, Object> cellConfig = new HashMap();
    cellConfig.put("config",configObject);
    customRow.setCustomCellConfig(cellConfig);
    section.addRow(customRow);
    ```

## Use annotations for models

1.  You can create a form from a POJO by adding annotations to your model properties
  
    ```java
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

    }
    
    ```
2.  Use "FormDescriptorAnnotationFactory" to create a FormDescriptor from your model
  
    ```java
    FormDescriptorAnnotationFactory factory = new FormDescriptorAnnotationFactory(getActivity());
    FormDescriptor descriptor = factory.createFormDescriptorFromAnnotatedClass(entry);
    ```

## Validation
1. Define custom validators by implementing ```FormValidator```
    ```java
    public class EmailValidator implements FormValidator {
	    private static final String EMAIL_PATTERN =
	            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	    @Override
	    public RowValidationError validate(RowDescriptor descriptor) {
	        Value value = descriptor.getValue();
	        if (value.getValue() != null && value.getValue() instanceof String) {
	            String val = (String) value.getValue();
	            return (val.matches(EMAIL_PATTERN)) ? null : new RowValidationError(descriptor,  R.string.validation_invalid_email);
	        }
	        return new RowValidationError(descriptor, R.string.validation_invalid_email);
	    }
}
    ```

2. With annotations
    Specify validator classes as an array of .class items 
    ```java
    @FormElement(
            label = R.string.email,
            rowDescriptorType = RowDescriptor.FormRowDescriptorTypeEmailInline,
            sortId = 2,
            validatorClasses = {EmailValidator.class, BlankStringValidator.class}
    )
    public String email;
    ```

3. Or add validators to rowdescriptors manually
    ```java
    RowDescriptor rowDescriptor = RowDescriptor.newInstance("valid",
	    RowDescriptor.FormRowDescriptorTypeEmail,
	    "Email Test",
	    new Value<String>("notavalidemail"));

    rowDescriptor.addValidator(new EmailValidator());
    ```

## Cell style configuration
The following preset cell configurations are available
```java
    public enum CONFIG_TYPE {
        PADDING, // Specified as an int[4] in the order {left, top, right, bottom}
        TYPEFACE,  // Specified as a Typeface enum such as Typeface.BOLD.  Only applicable to instances of TextView
        INPUT_TYPE,  // Specified as an InputType enum such as InputType.TYPE_NUMBER_FLAG_DECIMAL.  Only applicable to instances of TextView
        IME_OPTIONS,  // Specified as an imeOptions enum such as IME_ACTION_DONE.  Only applicable to instances of TextView
        TEXT_ALIGNMENT, // Specified as an View enum such as TEXT_ALIGNMENT_CENTER (only available on api 17+)
        MAX_LINES, // Specified as an int.  Only applicable to instances of TextView
        MIN_LINES, // Specified as an int.  Only applicable to instances of TextView
        MINIMUM_HEIGHT, // Specified as an int
        MINIMUM_WIDTH, // Specified as an int
        BACKGROUND_COLOR, // Specified as color reference id
        TEXT_COLOR, // Specified as color reference id.  Only applicable to instances of TextView
        GRAVITY, // Specified as a Gravity enum such as Gravity.CENTER.  Only applicable to instances of TextView
        VISIBILITY // Specified as a View enum such as VIEW.GONE or View.INVISIBLE
    }
```
They can be used on any view in a cell that has a public accessor.  In this example I modify the styles for the labelTextView and detailTextView of the FormRowDescriptorTypeDetail type cell -
```java
RowDescriptor rowDescriptor = RowDescriptor.newInstance("valid",
        RowDescriptor.FormRowDescriptorTypeDetail,
        "Test",
        new Value<String>("Test"));

HashMap<String, CellConfigObject[]> cellConfig = new HashMap<String, CellConfigObject[]>();
int[] padding = {12, 13, 14, 15};
CellConfigObject[] config = {
        new CellConfigObject(CellConfigObject.CONFIG_TYPE.PADDING, padding),
        new CellConfigObject(CellConfigObject.CONFIG_TYPE.TYPEFACE, Typeface.BOLD_ITALIC),
        new CellConfigObject(CellConfigObject.CONFIG_TYPE.INPUT_TYPE, InputType.TYPE_NUMBER_FLAG_DECIMAL),
        new CellConfigObject(CellConfigObject.CONFIG_TYPE.GRAVITY, Gravity.CENTER),
};
cellConfig.put("getLabelTextView", config); //getLabelTextView is the function name of the getter for this view
cellConfig.put("getDetailTextView", config);
rowDescriptor.setCellConfig(cellConfig);
```

another use case is to hide parts of an form cell such as the label
```java
HashMap<String, CellConfigObject[]> cellConfig = new HashMap<String, CellConfigObject[]>();
CellConfigObject[] config = {
    new CellConfigObject(CellConfigObject.CONFIG_TYPE.VISIBILITY, View.GONE)
};
cellConfig.put("getLabelTextView", config);
row.setCellConfig(cellConfig);
```

## Installation

QMBForm is not available at the maven repository yet. But it's a gradly based android-library project. 

1. Include the QMBFrom directory as a library module in your application
2. see sample application: 

Add this to your build.gradle dependencies section
  ```
  compile project(":lib:QMBForm")
  ```
Add this to your settings.gradle
  ```
  include ':app', ':lib:QMBForm'
  ```

QMBForm **has no** dependencies to other third party libs (but compile 'com.android.support:appcompat-v7:19.+') is needed

## Supported Form Elements
Most elements have an inline version (label on the same line as the displayed value) and a normal version (label on separate line above displayed value).

- Available elements:

  ```java
    public static final String FormRowDescriptorTypeText = "text";
    public static final String FormRowDescriptorTypeTextInline = "textInline";
    public static final String FormRowDescriptorTypeHTMLText = "htmlText";
    public static final String FormRowDescriptorTypeDetailInline = "detailInline";
    public static final String FormRowDescriptorTypeDetail = "detail";
    public static final String FormRowDescriptorTypeURL = "url";
    public static final String FormRowDescriptorTypeEmail = "email";
    public static final String FormRowDescriptorTypeEmailInline = "emailInline";
    public static final String FormRowDescriptorTypePassword = "password";
    public static final String FormRowDescriptorTypePasswordInline = "passwordInline";
    public static final String FormRowDescriptorTypeNumber = "number";
    public static final String FormRowDescriptorTypeNumberInline = "numberInline";
    public static final String FormRowDescriptorTypeIntegerSlider = "integerSlider";
    public static final String FormRowDescriptorTypeCurrency = "currency";
    public static final String FormRowDescriptorTypePhone = "phone";
    public static final String FormRowDescriptorTypeInteger = "integer";
    public static final String FormRowDescriptorTypeIntegerInline = "integerInline";
    public static final String FormRowDescriptorTypeTextView = "textView";
    public static final String FormRowDescriptorTypeTextViewInline = "textViewInline";
    
    public static final String FormRowDescriptorTypeSelectorSpinner = "selectorSpinner";
    public static final String FormRowDescriptorTypeSelectorSpinnerInline = "selectorSpinnerInline";
    public static final String FormRowDescriptorTypeSelectorTextPickerDialogInline = "textPickerDialog";
    public static final String FormRowDescriptorTypeSelectorPickerDialogInline = "selectorPickerDialog";
    public static final String FormRowDescriptorTypeSelectorPickerDialog = "selectorPickerDialogVertical";
    public static final String FormRowDescriptorTypeSelectorSegmentedControlInline = "selectorSegmentedControlInline";
    public static final String FormRowDescriptorTypeSelectorSegmentedControl = "selectorSegmentedControl";
    
    public static final String FormRowDescriptorTypeDateInline = "dateInline";
    public static final String FormRowDescriptorTypeTimeInline = "timeInline";
    public static final String FormRowDescriptorTypeDate = "date";
    public static final String FormRowDescriptorTypeTime = "time";
    public static final String FormRowDescriptorTypeDatePicker = "datePicker";
    
    public static final String FormRowDescriptorTypeBooleanCheck = "booleanCheck";
    public static final String FormRowDescriptorTypeBooleanSwitch = "booleanSwitch";
    public static final String FormRowDescriptorTypeButton = "button";
    public static final String FormRowDescriptorTypeButtonInline = "buttonInline";
    public static final String FormRowDescriptorTypeExternal = "external";
    public static final String FormRowDescriptorTypeSectionSeperator = "sectionSeperator";
    public static final String FormRowDescriptorTypeHtml = "html";
  ```
  
- Coming elements: (Avaiable at XLForm)

  ```java
    public static final String FormRowDescriptorTypeTwitter = "twitter";
    public static final String FormRowDescriptorTypeAccount = "account";
  
    public static final String FormRowDescriptorTypeSelectorPush = "selectorPush";
    public static final String FormRowDescriptorTypeSelectorActionSheet = "selectorActionSheet";
    public static final String FormRowDescriptorTypeSelectorAlertView = "selectorAlertView";
    public static final String FormRowDescriptorTypeSelectorPickerView = "selectorPickerView";
    public static final String FormRowDescriptorTypeSelectorPickerViewInline = "selectorPickerViewInline";
    
    public static final String FormRowDescriptorTypeMultipleSelector = "multipleSelector";
    public static final String FormRowDescriptorTypeSelectorLeftRight = "selectorLeftRight";
    
    public static final String FormRowDescriptorTypeDateTimeInline = "datetimeInline";
    public static final String FormRowDescriptorTypeDateTime = "datetime";
    
    public static final String FormRowDescriptorTypePicker = "picker";
    
    public static final String FormRowDescriptorTypeImage = "image";
    public static final String FormRowDescriptorTypeWeb = "web";
    public static final String FormRowDescriptorTypeStepCounter = "stepCounter";
  ```

## To do

- Simpler cell customisation
- More form elements
- Form model 

## Credits
QMBForm is based on the ideas of [XLForm](https://github.com/xmartlabs/XLForm) - the most flexible and powerful iOS library to create dynamic table-view forms.

Thanks guys!
