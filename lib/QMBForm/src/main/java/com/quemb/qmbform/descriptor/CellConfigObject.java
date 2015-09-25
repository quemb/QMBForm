package com.quemb.qmbform.descriptor;

/**
 * Created by pmaccamp on 9/24/2015.
 */
public class CellConfigObject {
    public enum CONFIG_TYPE {
        PADDING, // Specified as an int[4] in the order {left, top, right, bottom}
        TYPEFACE,  // Specified as a Typeface enum such as Typeface.BOLD
        INPUT_TYPE,  // Specified as an InputType enum such as InputType.TYPE_NUMBER_FLAG_DECIMAL
        TEXT_ALIGNMENT, // Specified as an View enum such as TEXT_ALIGNMENT_CENTER (only available on api 17+)
        MAX_LINES, // Specified as an int
        MIN_LINES, // Specified as an int
        MINIMUM_HEIGHT, // Specified as an int
        MINIMUM_WIDTH, // Specified as an int
        BACKGROUND_COLOR, // Specified as color reference id
        TEXT_COLOR, // Specified as color reference id
        GRAVITY // Specified as a Gravity enum such as Gravity.CENTER
    }

    public CONFIG_TYPE configType;
    public Object configValue;

    public CellConfigObject(CONFIG_TYPE configType, Object configValue) {
        this.configType = configType;
        this.configValue = configValue;
    }
}
