package com.quemb.qmbform.descriptor;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class SectionDescriptor extends FormItemDescriptor {

    private FormDescriptor mFormDescriptor;

    private ArrayList<RowDescriptor> mRows;

    public static SectionDescriptor newInstance(String tag){

        return SectionDescriptor.newInstance(tag, null);

    }

    public static SectionDescriptor newInstance(String tag, String title){

        SectionDescriptor descriptor = new SectionDescriptor();
        descriptor.mTitle = title;
        descriptor.mTag = tag;
        return descriptor;

    }

    public SectionDescriptor(){

        mRows = new ArrayList<RowDescriptor>();

    }

    public FormDescriptor getFormDescriptor() {
        return mFormDescriptor;
    }

    public void setFormDescriptor(FormDescriptor formDescriptor) {
        mFormDescriptor = formDescriptor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void addRow(RowDescriptor row){
        insertRowAtIndex(row, mRows.size());
    }

    public void addRow(RowDescriptor row, int index){
        insertRowAtIndex(row, index);
    }

    public void removeRow(RowDescriptor row){
        int index = mRows.indexOf(row);
        if (index>0){
            removeRowAtIndex(index);
        }
    }

    public int getRowCount(){
        return mRows.size();
    }

    public ArrayList<RowDescriptor> getRows(){
        return mRows;
    }

    private void insertRowAtIndex(RowDescriptor row, int index){
        if (mRows.size()>=index){
            row.setSectionDescriptor(this);
            mRows.add(index, row);
        }
    }

    private void removeRowAtIndex(int index){
        mRows.remove(index);
    }

    public boolean hasTitle() {
        return getTitle() != null && getTitle().length() > 0;
    }

    public RowDescriptor findRowDescriptor(String tag){
        RowDescriptor rowDescriptor = null;

        for (RowDescriptor iRowDescriptor:getRows()){
            if (tag.equals(iRowDescriptor.getTag())){
                rowDescriptor = iRowDescriptor;
                break;
            }
        }

        return rowDescriptor;
    }
}
