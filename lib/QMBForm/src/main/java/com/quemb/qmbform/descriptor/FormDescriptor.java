package com.quemb.qmbform.descriptor;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormDescriptor {

    private String mTitle;
    private ArrayList<SectionDescriptor> mSections;
    private OnFormRowValueChangedListener mOnFormRowValueChangedListener;

    public static FormDescriptor newInstance(){
        return FormDescriptor.newInstance(null);
    }

    public static FormDescriptor newInstance(String title){

        FormDescriptor descriptor = new FormDescriptor();
        descriptor.mTitle = title;
        return descriptor;

    }

    public FormDescriptor(){
        mSections = new ArrayList<SectionDescriptor>();
    }

    public void addSection(SectionDescriptor sectionDescriptor){
        insertSectionAtIndex(sectionDescriptor, mSections.size());
    }

    public void removeSection(SectionDescriptor sectionDescriptor){
        int index = mSections.indexOf(sectionDescriptor);
        if (index>=0){
            removeSectionAtIndex(index);
        }
    }

    public int countOfSections(){
        return mSections.size();
    }

    public SectionDescriptor sectionAtIndex(int index){
        if (mSections.size()>index){
            return mSections.get(index);
        }
        return null;
    }

    public ArrayList<SectionDescriptor> getSections(){
        return mSections;
    }

    public void insertSectionAtIndex(SectionDescriptor section, int index){
        section.setFormDescriptor(this);
        mSections.add(index, section);
    }

    private void removeSectionAtIndex(int index){
        mSections.remove(index);
    }

    public String getTitle() {
        return mTitle;
    }

    public OnFormRowValueChangedListener getOnFormRowValueChangedListener() {
        return mOnFormRowValueChangedListener;
    }

    public void setOnFormRowValueChangedListener(
            OnFormRowValueChangedListener onFormRowValueChangedListener) {
        mOnFormRowValueChangedListener = onFormRowValueChangedListener;
    }

}
