package com.quemb.qmbform.descriptor;

/**
 * Created by pmaccamp on 9/24/2015.
 */
public class CellConfigObject {
    public enum CONFIG_TYPE {
        PADDING,
        TYPEFACE,
        INPUT_TYPE,
        TEXT_ALIGNMENT,
        MAX_LINES,
        MIN_LINES,
        MINIMUM_HEIGHT,
        MINIMUM_WIDTH,
        BACKGROUND_COLOR,
        TEXT_COLOR,
        GRAVITY
    }

    public CONFIG_TYPE configType;
    public Object configValue;

    public CellConfigObject(CONFIG_TYPE configType, Object configValue) {
        this.configType = configType;
        this.configValue = configValue;
    }
}
