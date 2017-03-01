package com.quemb.qmbform.descriptor;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormDescriptor {

    private String mTitle;
    private HashMap<String, Object> mCellConfig;
    private ArrayList<SectionDescriptor> mSections;
    private OnFormRowValueChangedListener mOnFormRowValueChangedListener;
    private OnFormRowChangeListener mOnFormRowChangeListener;

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

    /**
     * Set CellConfig member
     */
    public void setCellConfig(HashMap<String, Object> cellConfig) {
        mCellConfig = cellConfig;
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

    public SectionDescriptor sectionByTag(String tag) {
        for (SectionDescriptor sectionDescriptor : mSections) {
            if (sectionDescriptor.getTag().equals(tag)) {
                return sectionDescriptor;
            }
        }
        return null;
    }

    public SectionDescriptor getSectionWithTitle(String title) {
        for (SectionDescriptor sectionDescriptor : mSections) {
            if (sectionDescriptor.getTitle().equals(title)) {
                return sectionDescriptor;
            }
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

    public RowDescriptor findRowDescriptor(String tag){
        RowDescriptor rowDescriptor = null;

        for (SectionDescriptor sectionDescriptor:getSections()){
            rowDescriptor = sectionDescriptor.findRowDescriptor(tag);
            if (rowDescriptor != null) break;
        }

        return rowDescriptor;
    }

    public void setOnFormRowValueChangedListener(
            OnFormRowValueChangedListener onFormRowValueChangedListener) {
        mOnFormRowValueChangedListener = onFormRowValueChangedListener;
    }

    public boolean isValid(Context context){

        FormValidation formValidation = getFormValidation(context);

        if (formValidation.getRowValidationErrors().size()>0){
            return false;
        }
        return true;
    }

    public FormValidation getFormValidation(Context context) {

        FormValidation formValidation = new FormValidation(context);
        for (SectionDescriptor sectionDescriptor : getSections()){
            for (RowDescriptor rowDescriptor : sectionDescriptor.getRows()){
                if (!rowDescriptor.isValid()){
                    formValidation.getRowValidationErrors().addAll(rowDescriptor.getValidationErrors());
                }
            }
        }
        return formValidation;

    }

    protected void didInsertRow(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor){
        if (mOnFormRowChangeListener != null){
            mOnFormRowChangeListener.onRowAdded(rowDescriptor, sectionDescriptor);
        }
    }

    protected void didRemoveRow(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor){
        if (mOnFormRowChangeListener != null){
            mOnFormRowChangeListener.onRowRemoved(rowDescriptor, sectionDescriptor);
        }
    }


    protected OnFormRowChangeListener getOnFormRowChangeListener() {
        return mOnFormRowChangeListener;
    }

    public void setOnFormRowChangeListener(OnFormRowChangeListener onFormRowChangeListener) {
        mOnFormRowChangeListener = onFormRowChangeListener;
    }

    public Map<String, Object> getFormValues() {
        Map<String, Object> m = new HashMap<String, Object>();
        for (SectionDescriptor section : getSections()) {
            for (RowDescriptor row : section.getRows()) {
                m.put(row.getTag(), row.getValueData());
            }
        }
        return m;
    }

}