package com.quemb.qmbform.descriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class SectionDescriptor extends FormItemDescriptor {

    private FormDescriptor mFormDescriptor;
    private ArrayList<RowDescriptor<?>> mRows;
    private Boolean mMultivalueSection = false;

    public static SectionDescriptor newInstance(String tag) {

        return SectionDescriptor.newInstance(tag, null);

    }

    public static SectionDescriptor newInstance(String tag, String title) {

        SectionDescriptor descriptor = new SectionDescriptor();
        descriptor.mTitle = title;
        descriptor.mTag = tag;
        return descriptor;

    }

    public SectionDescriptor() {

        mRows = new ArrayList<RowDescriptor<?>>();

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

    public void addRow(RowDescriptor<?> row) {
        addRow(row, mRows.size());
    }

    public void addRow(RowDescriptor<?> row, int index) {
        insertRowAtIndex(row, index);

        // Propagate the CellConfig from Section to Row

        HashMap<String, Object> cellConfig = getCellConfig();
        if (cellConfig != null)
            row.setCellConfig(cellConfig);
    }

    public void addRow(RowDescriptor<?> row, HashMap<String, Object> cellConfig) {
        addRow(row, mRows.size());

        if (cellConfig != null)
            row.setCellConfig(cellConfig);
    }

    public void removeRow(RowDescriptor<?> row) {
        int index = mRows.indexOf(row);
        removeRowAtIndex(index);
    }

    public int getRowCount() {
        return mRows.size();
    }

    public List<RowDescriptor<?>> getRows() {
        return mRows;
    }

    private void insertRowAtIndex(RowDescriptor<?> row, int index) {
        if (mRows.size() >= index) {
            row.setSectionDescriptor(this);
            mRows.add(index, row);
            if (getFormDescriptor() != null) {
                getFormDescriptor().didInsertRow(row, this);
            }

        }
    }

    private void removeRowAtIndex(int index) {
        RowDescriptor<?> rowDescriptor = mRows.get(index);
        mRows.remove(index);
        if (getFormDescriptor() != null) {
            getFormDescriptor().didRemoveRow(rowDescriptor, this);
        }

    }

    public boolean hasTitle() {
        return getTitle() != null && getTitle().length() > 0;
    }

    public RowDescriptor<?> findRowDescriptor(String tag) {
        RowDescriptor<?> rowDescriptor = null;

        for (RowDescriptor<?> iRowDescriptor : getRows()) {
            if (tag.equals(iRowDescriptor.getTag())) {
                rowDescriptor = iRowDescriptor;
                break;
            }
        }

        return rowDescriptor;
    }

    public int getIndexOfRowDescriptor(RowDescriptor<?> rowDescriptor) {
        return mRows.indexOf(rowDescriptor);
    }

    public Boolean isMultivalueSection() {
        return mMultivalueSection;
    }

    public void setMultivalueSection(Boolean multivalueSection) {
        mMultivalueSection = multivalueSection;
    }

    public List getRowValues() {

        ArrayList<Object> values = new ArrayList<>();
        for (RowDescriptor<?> rowDescriptor : mRows) {
            if (rowDescriptor.getValue() != null && rowDescriptor.getValueData() != null) {
                values.add(rowDescriptor.getValueData());
            }
        }
        return values;

    }
}
