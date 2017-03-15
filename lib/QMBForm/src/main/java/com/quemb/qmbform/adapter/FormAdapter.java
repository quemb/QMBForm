package com.quemb.qmbform.adapter;

import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.CellViewFactory;
import com.quemb.qmbform.view.Cell;
import com.quemb.qmbform.view.FormEditTextFieldCell;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormAdapter extends BaseAdapter {

    private FormDescriptor mFormDescriptor;
    private ArrayList<FormItemDescriptor> mItems;
    private Context mContext;
    private Boolean mEnableSectionSeperator;
    private int focusedEditTextRow = -1;

    public static FormAdapter newInstance(FormDescriptor formDescriptor, Context context){
        FormAdapter formAdapter = new FormAdapter();
        formAdapter.mFormDescriptor = formDescriptor;
        formAdapter.mContext = context;
        formAdapter.setEnableSectionSeperator(true);
        formAdapter.setup();

        return formAdapter;
    }

    public void setup() {
        mItems = new ArrayList<FormItemDescriptor>();
        int sectionCount = 1;
        for (SectionDescriptor sectionDescriptor : mFormDescriptor.getSections()){

            if (sectionDescriptor.hasTitle()){
                mItems.add(sectionDescriptor);
            }

            mItems.addAll(sectionDescriptor.getRows());

            if (getEnableSectionSeperator() && sectionCount < mFormDescriptor.getSections().size()) {

                FormItemDescriptor itemDescriptor = mItems.get(mItems.size() - 1);
                if (itemDescriptor instanceof RowDescriptor)
                    ((RowDescriptor) itemDescriptor).setLastRowInSection(true);

                mItems.add(RowDescriptor.newInstance(null, RowDescriptor.FormRowDescriptorTypeSectionSeperator));
            }
            sectionCount++;
        }
    }
    
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public FormItemDescriptor getItem(int position) {

        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell = CellViewFactory.getInstance().createViewForFormItemDescriptor(mContext,getItem(position));
        if (cell instanceof FormEditTextFieldCell) {
            processEditTextCell((FormEditTextFieldCell)cell, position);
        }
        return cell;
    }

    private void processEditTextCell(FormEditTextFieldCell cell, final int row) {
        if (row == focusedEditTextRow) {
            cell.getEditView().requestFocus();
        }
        cell.getEditView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusedEditTextRow = row;
                }
            }
        });
    }


    public Boolean getEnableSectionSeperator() {
        return mEnableSectionSeperator;
    }

    public void setEnableSectionSeperator(Boolean enableSectionSeperator) {
        mEnableSectionSeperator = enableSectionSeperator;
    }
}
