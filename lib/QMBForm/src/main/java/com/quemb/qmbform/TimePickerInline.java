package com.quemb.qmbform;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TimePicker;

/**
 * TimePicker that does not fight for scrolling in a ScrollView.
 * Created by MTL / PTN on 19/07/16.
 */
public class TimePickerInline extends TimePicker
{
    public TimePickerInline(Context context) {
        super(context);
    }

    public TimePickerInline(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimePickerInline(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimePickerInline(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Stop ScrollView from getting involved once you interact with the View
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
}
