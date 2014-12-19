package com.quemb.qmbform;

import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.view.Cell;
import com.quemb.qmbform.view.FormBaseCell;
import com.quemb.qmbform.view.FormBooleanFieldCell;
import com.quemb.qmbform.view.FormButtonFieldCell;
import com.quemb.qmbform.view.FormCheckFieldCell;
import com.quemb.qmbform.view.FormDateDialogFieldCell;
import com.quemb.qmbform.view.FormDateInlineFieldCell;
import com.quemb.qmbform.view.FormDetailTextFieldCell;
import com.quemb.qmbform.view.FormDetailTextVerticalFieldCell;
import com.quemb.qmbform.view.FormEditCurrencyFieldCell;
import com.quemb.qmbform.view.FormEditEmailFieldCell;
import com.quemb.qmbform.view.FormEditHTMLTextViewFieldCell;
import com.quemb.qmbform.view.FormEditIntegerFieldCell;
import com.quemb.qmbform.view.FormEditNumberFieldCell;
import com.quemb.qmbform.view.FormEditPasswordFieldCell;
import com.quemb.qmbform.view.FormEditPhoneFieldCell;
import com.quemb.qmbform.view.FormEditTextFieldCell;
import com.quemb.qmbform.view.FormEditTextViewFieldCell;
import com.quemb.qmbform.view.FormEditURLFieldCell;
import com.quemb.qmbform.view.FormExternalButtonFieldCell;
import com.quemb.qmbform.view.FormIntegerSliderFieldCell;
import com.quemb.qmbform.view.FormPickerDialogFieldCell;
import com.quemb.qmbform.view.FormPickerDialogVerticalFieldCell;
import com.quemb.qmbform.view.FormSpinnerFieldCell;
import com.quemb.qmbform.view.FormTimeDialogFieldCell;
import com.quemb.qmbform.view.FormTimeInlineFieldCell;
import com.quemb.qmbform.view.SectionCell;

import android.content.Context;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class CellViewFactory {

    private static CellViewFactory instance = null;
    private HashMap<String, Class <? extends FormBaseCell>> mViewRowTypeMap;

    public static CellViewFactory getInstance() {
        if (instance == null) {
            instance = new CellViewFactory();
        }
        return instance;
    }

    public CellViewFactory(){

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;

        mViewRowTypeMap = new HashMap<String, Class<? extends FormBaseCell>>();
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeName, FormDetailTextFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeNameVertical, FormDetailTextVerticalFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeText, FormEditTextFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeTextView, FormEditTextViewFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeBooleanSwitch, currentapiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? FormBooleanFieldCell.class : FormCheckFieldCell.class );
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeBooleanCheck, FormCheckFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeButton, FormButtonFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeDate, FormDateDialogFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeDatePicker, FormDateDialogFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeDateInline, FormDateInlineFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeTime, FormTimeDialogFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeTimeInline, FormTimeInlineFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeNumber, FormEditNumberFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeCurrency, FormEditCurrencyFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeInteger, FormEditIntegerFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypePhone, FormEditPhoneFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeURL, FormEditURLFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypePassword, FormEditPasswordFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeEmail, FormEditEmailFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeSelectorSpinner, FormSpinnerFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog, FormPickerDialogFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeSelectorPickerDialogVertical, FormPickerDialogVerticalFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeIntegerSlider, FormIntegerSliderFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeExternal, FormExternalButtonFieldCell.class);
        mViewRowTypeMap.put(RowDescriptor.FormRowDescriptorTypeHTMLText,FormEditHTMLTextViewFieldCell.class);

    }

    public Cell createViewForFormItemDescriptor(Context context, FormItemDescriptor descriptor){

        Cell rowView = null;

        if (descriptor instanceof SectionDescriptor){

            SectionCell sectionCell = new SectionCell(context, (SectionDescriptor) descriptor);
            rowView = sectionCell;

        } else if (descriptor instanceof RowDescriptor){
            RowDescriptor row = (RowDescriptor) descriptor;
            try {
                FormBaseCell formBaseCell;

                formBaseCell = mViewRowTypeMap.get(row.getRowType()).getConstructor(Context.class, RowDescriptor.class).newInstance(
                        context, row);
                rowView = formBaseCell;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }

        return rowView;

    }

    public void setRowTypeMap(String descriptorType, Class<? extends FormBaseCell> clazz){
        mViewRowTypeMap.put(descriptorType, clazz);
    }

}
