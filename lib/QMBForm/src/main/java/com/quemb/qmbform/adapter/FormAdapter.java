package com.quemb.qmbform.adapter;

import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.CellViewFactory;

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

    public static FormAdapter newInstance(FormDescriptor formDescriptor, Context context){
        FormAdapter formAdapter = new FormAdapter();
        formAdapter.mFormDescriptor = formDescriptor;
        formAdapter.mContext = context;
        return formAdapter;
    }

    @Override
    public int getCount() {
        mItems = new ArrayList<FormItemDescriptor>();
        for (SectionDescriptor sectionDescriptor : mFormDescriptor.getSections()){

            if (sectionDescriptor.hasTitle()){
                mItems.add(sectionDescriptor);
            }

            mItems.addAll(sectionDescriptor.getRows());
        }

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
        return CellViewFactory.getInstance().createViewForFormItemDescriptor(mContext,getItem(position));
    }
}
