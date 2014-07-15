package com.quemb.qmbform;

import com.quemb.qmbform.adapter.FormAdapter;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.view.Cell;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.Normalizer;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormManager {

    private FormDescriptor mFormDescriptor;
    private ListView mListView;
    private OnFormRowClickListener mOnFormRowClickListener;

    public FormManager(){

    }

    public void setup(FormDescriptor formDescriptor, final ListView listView, Context context){

//        mFormDescriptor = formDescriptor;
//        mListView = listView;

        final FormAdapter adapter = FormAdapter.newInstance(formDescriptor, context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FormItemDescriptor itemDescriptor = adapter.getItem(position);

                Cell cell = itemDescriptor.getCell();
                if (cell != null){
                    cell.onCellSelected();
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


    }

    public OnFormRowClickListener getOnFormRowClickListener() {
        return mOnFormRowClickListener;
    }

    public void setOnFormRowClickListener(OnFormRowClickListener onFormRowClickListener) {
        mOnFormRowClickListener = onFormRowClickListener;
    }
}
