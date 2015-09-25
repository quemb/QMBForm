package com.quemb.qmbform.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellConfigObject;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.OnValueChangeListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public abstract class FormBaseCell extends Cell {


    private static final int REMOVE_BUTTON_ID = R.id.end;
    private static final int ADD_BUTTON_ID = R.id.beginning;


    private LinearLayout mMultiValueWrapper;

    public FormBaseCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();

        if (getRowDescriptor() != null && getRowDescriptor().getValue() != null) {
            getRowDescriptor().getValue().setOnValueChangeListener(new OnValueChangeListener() {
                @Override
                public void onChange(Object value) {
                    update();
                }
            });
        }
    }

    @Override
    protected void afterInit() {
        super.afterInit();

        // Update elements using cell config
        if (getFormItemDescriptor().getCellConfig() != null) {
            for (String functionName : getFormItemDescriptor().getCellConfig().keySet()) {
                try {
                    Method method = findMethodWithName(getClass(), functionName);
                    if (method != null) {
                        View view = (View) method.invoke(this);

                        CellConfigObject[] configs = getFormItemDescriptor().getCellConfig().get(functionName);

                        for (CellConfigObject config : configs) {
                            applyConfig(view, config);
                        }
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Method findMethodWithName(Class clazz, String methodName) {
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            return method;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null) {
                return findMethodWithName(clazz.getSuperclass(), methodName);
            }
        }
        return null;
    }

    private void applyConfig(View view, CellConfigObject config) {
        switch (config.configType) {
            case PADDING:
                int[] padding = (int[]) config.configValue;
                view.setPadding(padding[0], padding[1], padding[2], padding[3]);
                break;
            case TYPEFACE:
                int typeface = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(null, typeface);
                }
                break;
            case INPUT_TYPE:
                int inputType = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setInputType(inputType);
                }
                break;
            case TEXT_ALIGNMENT:
                int textAlignment = (int) config.configValue;
                // Only supported on api 17 and higher
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    view.setTextAlignment(textAlignment);
                }
                break;
            case MAX_LINES:
                int maxLines = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setMaxLines(maxLines);
                }
                break;
            case MIN_LINES:
                int minLines = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setMinLines(minLines);
                }
                break;
            case MINIMUM_HEIGHT:
                int minHeight = (int) config.configValue;
                view.setMinimumHeight(minHeight);
                break;
            case MINIMUM_WIDTH:
                int minWidth = (int) config.configValue;
                view.setMinimumWidth(minWidth);
                break;
            case BACKGROUND_COLOR:
                int color = (int) config.configValue;
                view.setBackgroundColor(color);
                break;
            case TEXT_COLOR:
                int textColor = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(textColor);
                }
                break;
            case GRAVITY:
                int gravity = (int) config.configValue;
                if (view instanceof TextView) {
                    ((TextView) view).setGravity(gravity);
                }
                break;
        }
    }

    protected ViewGroup getSuperViewForLayoutInflation() {

        if (getRowDescriptor().getSectionDescriptor() != null && this.getRowDescriptor().getSectionDescriptor().isMultivalueSection()) {
            LinearLayout linearLayout = createMultiValueWrapper();
            addView(linearLayout);
            return linearLayout;
        }
        return super.getSuperViewForLayoutInflation();
    }

    private LinearLayout createMultiValueWrapper() {

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setId(R.id.wrap_content);
        linearLayout.setFocusable(false);
        linearLayout.setFocusableInTouchMode(false);

        ImageButton deleteButton = new ImageButton(getContext());
        deleteButton.setId(REMOVE_BUTTON_ID);
        deleteButton.setFocusableInTouchMode(false);
        deleteButton.setFocusable(false);

        Drawable removeIcon = getContext().getResources().getDrawable(R.drawable.ic_action_remove);
        removeIcon.setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);

        deleteButton.setImageDrawable(removeIcon);
        deleteButton.setBackgroundColor(Color.TRANSPARENT);
        deleteButton.setVisibility(VISIBLE);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SectionDescriptor sectionDescriptor = getRowDescriptor().getSectionDescriptor();
                sectionDescriptor.removeRow(getRowDescriptor());

            }
        });
        linearLayout.addView(deleteButton);

        ImageButton addButton = new ImageButton(getContext());
        addButton.setId(ADD_BUTTON_ID);
        addButton.setFocusableInTouchMode(false);
        addButton.setFocusable(false);

        Drawable addIcon = getContext().getResources().getDrawable(R.drawable.ic_action_new);
        addIcon.setColorFilter(0xff00ff00, PorterDuff.Mode.MULTIPLY);


        addButton.setImageDrawable(addIcon);
        addButton.setBackgroundColor(Color.TRANSPARENT);
        addButton.setVisibility(GONE);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionDescriptor sectionDescriptor = getRowDescriptor().getSectionDescriptor();
                sectionDescriptor.addRow(RowDescriptor.newInstance(getRowDescriptor()));
            }
        });
        linearLayout.addView(addButton);

        SectionDescriptor sectionDescriptor = getRowDescriptor().getSectionDescriptor();
        int index = sectionDescriptor.getIndexOfRowDescriptor(getRowDescriptor());
        if (index == sectionDescriptor.getRowCount() - 1) {
            addButton.setVisibility(VISIBLE);
            deleteButton.setVisibility(GONE);
        } else {
            addButton.setVisibility(GONE);
            deleteButton.setVisibility(VISIBLE);
        }

        mMultiValueWrapper = linearLayout;

        return mMultiValueWrapper;
    }

    @Override
    public void lastInSection() {


    }

    public RowDescriptor getRowDescriptor() {
        return (RowDescriptor) getFormItemDescriptor();
    }

    public void onValueChanged(Value<?> newValue) {
        RowDescriptor row = getRowDescriptor();
        Value<?> oldValue = row.getValue();
        if (oldValue == null || newValue == null || !newValue.getValue().equals(oldValue.getValue())) {
            OnFormRowValueChangedListener listener = getRowDescriptor().getSectionDescriptor().getFormDescriptor().getOnFormRowValueChangedListener();
            row.setValue(newValue);
            if (listener != null) {
                listener.onValueChanged(row, oldValue, newValue);
            }
        }

    }


}
