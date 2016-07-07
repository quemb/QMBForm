package com.quemb.qmbform.descriptor;

/**
 * Colors and TextAppearance to be used by RowDescriptor.
 * Added by MTL / PTN on 02/07/2016
 */
public class CellDescriptor
{
    // TextAppearance style ID

    public static final String APPEARANCE_SECTION    = "FORM_APPEARANCE_SECTION";     // value is an attribute ID as Integer.valueOf(R.attr.xxx)
    public static final String APPEARANCE_TEXT_LABEL = "FORM_APPEARANCE_TEXT_LABEL";  // value is an attribute ID as Integer.valueOf(R.attr.xxx)
    public static final String APPEARANCE_TEXT_VALUE = "FORM_APPEARANCE_TEXT_VALUE";  // value is an attribute ID as Integer.valueOf(R.attr.xxx)
    public static final String APPEARANCE_BUTTON     = "FORM_APPEARANCE_BUTTON";      // value is an attribute ID as Integer.valueOf(R.attr.xxx)

    // Text colors (if TextAppearance styles are not defined)

    // Note: default TextView color is style android:textColor,
    // default EditText color is android:editTextColor (Lollipop+) or android:textColorPrimary (pre-Lollipop)

    public static final String COLOR_LABEL = "FORM_COLOR_LABEL";    // value is color as Integer.valueOf(OxAARRGGBB)
    public static final String COLOR_VALUE = "FORM_COLOR_VALUE";    // value is color as Integer.valueOf(OxAARRGGBB)

    // Disabled text colors

    public static final String COLOR_LABEL_DISABLED = "FORM_COLOR_LABEL_DISABLED";    // value is color as Integer.valueOf(OxAARRGGBB)
    public static final String COLOR_VALUE_DISABLED = "FORM_COLOR_VALUE_DISABLED";    // value is color as Integer.valueOf(OxAARRGGBB)
}
