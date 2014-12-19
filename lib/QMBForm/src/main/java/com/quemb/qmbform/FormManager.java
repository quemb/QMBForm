package com.quemb.qmbform;

import com.quemb.qmbform.adapter.FormAdapter;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.view.Cell;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.Normalizer;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormManager {

    private FormDescriptor mFormDescriptor;
    protected ListView mListView;
    protected OnFormRowClickListener mOnFormRowClickListener;

    public FormManager(){

    }


    public void setup(FormDescriptor formDescriptor, final ListView listView, Activity activity){

        Context context = activity;

//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        final FormAdapter adapter = FormAdapter.newInstance(formDescriptor, context);
        listView.setAdapter(adapter);
        listView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FormItemDescriptor itemDescriptor = adapter.getItem(position);

                Cell cell = itemDescriptor.getCell();
                if (cell != null && itemDescriptor instanceof RowDescriptor){
                    RowDescriptor rowDescriptor = (RowDescriptor) itemDescriptor;
                    if (!rowDescriptor.getDisabled()){
                        cell.onCellSelected();
                    }
                }

                OnFormRowClickListener descriptorListener = itemDescriptor.getOnFormRowClickListener();
                if (descriptorListener != null){
                    descriptorListener.onFormRowClick(itemDescriptor);
                }

                if (mOnFormRowClickListener != null){
                    mOnFormRowClickListener.onFormRowClick(itemDescriptor);
                }
            }
        });
        mListView = listView;

    }

    public OnFormRowClickListener getOnFormRowClickListener() {
        return mOnFormRowClickListener;
    }

    public void setOnFormRowClickListener(OnFormRowClickListener onFormRowClickListener) {
        mOnFormRowClickListener = onFormRowClickListener;
    }

    public void updateRows(){
        FormAdapter adapter = (FormAdapter) mListView.getAdapter();
        adapter.notifyDataSetChanged();
    }


}
