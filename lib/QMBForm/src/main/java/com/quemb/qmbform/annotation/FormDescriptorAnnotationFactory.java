package com.quemb.qmbform.annotation;

import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.view.Cell;
import com.quemb.qmbform.view.FormBaseCell;
import com.quemb.qmbform.view.FormBooleanFieldCell;
import com.quemb.qmbform.view.FormButtonFieldCell;
import com.quemb.qmbform.view.FormCheckFieldCell;
import com.quemb.qmbform.view.FormDateDialogFieldCell;
import com.quemb.qmbform.view.FormDateInlineFieldCell;
import com.quemb.qmbform.view.FormDetailTextFieldCell;
import com.quemb.qmbform.view.FormEditEmailFieldCell;
import com.quemb.qmbform.view.FormEditIntegerFieldCell;
import com.quemb.qmbform.view.FormEditNumberFieldCell;
import com.quemb.qmbform.view.FormEditPasswordFieldCell;
import com.quemb.qmbform.view.FormEditPhoneFieldCell;
import com.quemb.qmbform.view.FormEditTextFieldCell;
import com.quemb.qmbform.view.FormEditTextViewFieldCell;
import com.quemb.qmbform.view.FormEditURLFieldCell;
import com.quemb.qmbform.view.FormPickerDialogFieldCell;
import com.quemb.qmbform.view.FormSpinnerFieldCell;
import com.quemb.qmbform.view.FormTimeDialogFieldCell;
import com.quemb.qmbform.view.FormTimeInlineFieldCell;
import com.quemb.qmbform.view.SectionCell;

import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormDescriptorAnnotationFactory {




    private Context mContext;

    public FormDescriptorAnnotationFactory(Context context){

        mContext = context;

    }

    public Context getContext() {
        return mContext;
    }

    public FormDescriptor createFormDescriptorFromAnnotatedClass(Object object){

        FormDescriptor formDescriptor = FormDescriptor.newInstance();


        Class objClass = object.getClass();

        ArrayList<Field> declaredFields = getAllFields(new ArrayList<Field>(),objClass);
        ArrayList<Field> formFields = new ArrayList<Field>();
//        HashMap<String, ArrayList<Field>> sectionMap = new HashMap<String, ArrayList<Field>>();

        ArrayList<Section> sections = new ArrayList<Section>();


        /**
         * Get all annotated class fields
         */
        for (Field field:declaredFields){
            if (field.getAnnotation(FormElement.class)!=null){
                formFields.add(field);
            }
        }

        Collections.sort(formFields, new Comparator<Field>() {
            @Override
            public int compare(Field lhs, Field rhs) {

                FormElement annotationLhs = lhs.getAnnotation(FormElement.class);
                FormElement annotationRhs = rhs.getAnnotation(FormElement.class);
                if (annotationLhs.sortId() > annotationRhs.sortId()) {
                    return 1;
                } else if (annotationLhs.sortId() < annotationRhs.sortId()) {
                    return -1;
                }

                return 0;

            }
        });

        /**
         * Get all annotated class fields
         */
        for (Field field:formFields){
            FormElement annotation = field.getAnnotation(FormElement.class);
            String sectionTitle = annotation.section() == android.R.string.untitled ? "" : getContext().getString(annotation.section());

            Section section = null;
            for(Section iterateSection: sections){
                if (iterateSection.title == sectionTitle){
                    section = iterateSection;
                }
            }
            if (section == null) {
                section = new Section(sectionTitle);
                sections.add(section);
            }

            section.fields.add(field);
        }

        /**
         * Sort fields by sortId - annotation property
         */
        for (Section section : sections){
            ArrayList<Field> fields = section.fields;

            SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance(section.tag, section.title);

            for (Field field:fields) {

                FormElement annotation = field.getAnnotation(FormElement.class);
                if (annotation != null){

                    Value<Object> value = null;
                    try {
                        value = new Value<Object>(field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    RowDescriptor rowDescriptor = RowDescriptor.newInstance(annotation.tag().length() > 0 ? annotation.tag() : field.getName(),
                            annotation.rowDescriptorType(),
                            getContext().getString(annotation.label()),
                            value);
                    rowDescriptor.setHint(annotation.hint());
                    rowDescriptor.setRequired(annotation.required());
                    rowDescriptor.setDisabled(annotation.disabled());

                    boolean shouldAdd = true;
                    if (object instanceof FormElementDelegate){
                        FormElementDelegate delegate = (FormElementDelegate) object;
                        shouldAdd = delegate.shouldAddRowDescriptorForField(rowDescriptor, field);
                    }

                    if (shouldAdd){
                        sectionDescriptor.addRow( rowDescriptor ) ;
                    }

                }

            }

            formDescriptor.addSection(sectionDescriptor);

        }


        return formDescriptor;

    }

    public ArrayList<Field> getAllFields(ArrayList<Field> fields, Class<?> type) {
        for (Field field: type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private String toCamelCase(String s){
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    private String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }


    private class Section {

        public String title = "";
        public String tag = "";
        public ArrayList<Field> fields = new ArrayList<Field>();;

        public Section(String sectionTitle) {
            if (sectionTitle != null && sectionTitle.length()>0){
                title = sectionTitle;
                tag = toCamelCase(sectionTitle);
            }

        }
    }
}
