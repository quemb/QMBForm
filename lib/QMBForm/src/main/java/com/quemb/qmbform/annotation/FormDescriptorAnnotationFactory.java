package com.quemb.qmbform.annotation;

import android.content.Context;

import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormDescriptorAnnotationFactory {


    private Context mContext;

    public FormDescriptorAnnotationFactory(Context context) {

        mContext = context;

    }

    public Context getContext() {
        return mContext;
    }

    public FormDescriptor createFormDescriptorFromAnnotatedClass(Object object) {
        Class objClass = object.getClass();

        ArrayList<Field> declaredFields = getAllFields(new ArrayList<Field>(), objClass);

        return createFormDescriptorFromFields(declaredFields, object);
    }

    public FormDescriptor createFormDescriptorFromFields(ArrayList<Field> fields, Object object) {
        FormDescriptor formDescriptor = FormDescriptor.newInstance();

        ArrayList<Field> formFields = new ArrayList<Field>();
        ArrayList<Section> sections = new ArrayList<Section>();

        /**
         * Get all annotated class fields
         */
        for (Field field : fields) {
            if (field.getAnnotation(FormElement.class) != null) {
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
        for (Field field : formFields) {
            FormElement annotation = field.getAnnotation(FormElement.class);
            String sectionTitle = annotation.section() == android.R.string.untitled ? "" : getContext().getString(annotation.section());

            Section section = null;
            for (Section iterateSection : sections) {
                if (iterateSection.title == sectionTitle) {
                    section = iterateSection;
                }
            }
            if (section == null) {
                section = new Section(sectionTitle);
                sections.add(section);
            }
            if (!section.multiValue) {
                section.multiValue = annotation.multiValue();
            }

            section.fields.add(field);
        }

        /**
         * Sort fields by sortId - annotation property
         */
        for (Section section : sections) {
            ArrayList<Field> sectionFields = section.fields;

            SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance(section.tag, section.title);
            sectionDescriptor.setMultivalueSection(section.multiValue);

            for (Field field : sectionFields) {

                FormElement annotation = field.getAnnotation(FormElement.class);
                if (annotation != null) {
                    Value<Object> value = null;
                    try {
                        value = new Value<Object>(field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    if (section.multiValue) {
                        sectionDescriptor.setTag(field.getName());
                        ArrayList<FormOptionsObject> selectorOptions = convertFormOptionsAnnotation(
                                annotation.selectorOptions());

                        int index = 0;
                        if ((value != null ? value.getValue() : null) instanceof ArrayList) {
                            ArrayList<Object> list = (ArrayList<Object>) value.getValue();
                            for (Object item : list) {
                                RowDescriptor rowDescriptor = RowDescriptor.newInstance(annotation.tag().length() > 0 ? annotation.tag() : field.getName() + index,
                                        annotation.rowDescriptorType());
                                rowDescriptor.setValue(new Value<Object>(item));
                                rowDescriptor.setHint(annotation.hint());
                                rowDescriptor.setSelectorOptions(selectorOptions);
                                sectionDescriptor.addRow(rowDescriptor);
                                index++;
                            }
                        }
                        RowDescriptor rowDescriptor = RowDescriptor.newInstance(annotation.tag().length() > 0 ? annotation.tag() : field.getName() + ++index,
                                annotation.rowDescriptorType());
                        rowDescriptor.setHint(annotation.hint());
                        rowDescriptor.setSelectorOptions(selectorOptions);
                        addValidators(rowDescriptor, annotation);
                        sectionDescriptor.addRow(rowDescriptor);
                    } else {
                        RowDescriptor rowDescriptor = RowDescriptor.newInstance(annotation.tag().length() > 0 ? annotation.tag() : field.getName(),
                                annotation.rowDescriptorType(),
                                getContext().getString(annotation.label()),
                                value);
                        rowDescriptor.setHint(annotation.hint());
                        rowDescriptor.setRequired(annotation.required());
                        rowDescriptor.setDisabled(annotation.disabled());
                        rowDescriptor.setSelectorOptions(convertFormOptionsAnnotation(
                                annotation.selectorOptions()));
                        addValidators(rowDescriptor, annotation);
                        boolean shouldAdd = true;
                        if (object instanceof FormElementDelegate) {
                            FormElementDelegate delegate = (FormElementDelegate) object;
                            shouldAdd = delegate.shouldAddRowDescriptorForField(rowDescriptor, field);
                        }

                        if (shouldAdd) {
                            sectionDescriptor.addRow(rowDescriptor);
                        }
                    }
                }
            }

            formDescriptor.addSection(sectionDescriptor);
        }

        return formDescriptor;

    }

    public void addValidators(RowDescriptor rowDescriptor, FormElement annotation) {
        for (Class validator : annotation.validatorClasses()) {
            try {
                rowDescriptor.addValidator((FormValidator) validator.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<FormOptionsObject> convertFormOptionsAnnotation(FormOptionsObjectElement[] options) {
        ArrayList<FormOptionsObject> optionsArrayList = new ArrayList<FormOptionsObject>();
        for (FormOptionsObjectElement option : options) {
            if (option.valueType() == FormOptionsObjectElement.ValueTypes.INT) {
                optionsArrayList.add(FormOptionsObject.createFormOptionsObject(Integer.valueOf(option.value()), option.displayText()));
            } else if (option.valueType() == FormOptionsObjectElement.ValueTypes.DOUBLE) {
                optionsArrayList.add(FormOptionsObject.createFormOptionsObject(Double.valueOf(option.value()), option.displayText()));
            } else {
                optionsArrayList.add(FormOptionsObject.createFormOptionsObject(option.value(), option.displayText()));
            }
        }
        return optionsArrayList;
    }

    public ArrayList<Field> getAllFields(ArrayList<Field> fields, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private String toCamelCase(String s) {
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts) {
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
        public Boolean multiValue = false;
        public ArrayList<Field> fields = new ArrayList<Field>();
        ;

        public Section(String sectionTitle) {
            if (sectionTitle != null && sectionTitle.length() > 0) {
                title = sectionTitle;
                tag = toCamelCase(sectionTitle);
            }

        }
    }
}
