package com.quemb.qmbform.sample.controller;

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.annotation.FormDescriptorAnnotationFactory;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.sample.R;
import com.quemb.qmbform.sample.model.Entry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 17.07.14.
 */
public class SampleAnnotationFormFragment extends Fragment implements OnFormRowValueChangedListener,
        OnFormRowClickListener{

    private ListView mListView;
    private HashMap<String, Value<?>> mChangesMap = new HashMap<String, Value<?>>();;
    private MenuItem mSaveMenuItem;

    public static String TAG = "SampleFormFragment";

    public static final SampleAnnotationFormFragment newInstance()
    {
        SampleAnnotationFormFragment f = new SampleAnnotationFormFragment();

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.form_sample, container, false);

        mListView = (ListView) v.findViewById(R.id.list);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Entry entry = new Entry();
        entry.title = "Hello";
        entry.description = "World";
        entry.date = new Date();
        entry.dateInline = new Date();
        entry.multiValue = new ArrayList<String>();
        entry.multiValue.add("multiple");
        entry.multiValue.add("tags");
        entry.multiValue.add("allowed");

        // More styles and colors for cells
        HashMap<String, Object> cellConfig = new HashMap<>(8);

        // TextAppearance for section, label, value and button
        //cellConfig.put(CellDescriptor.APPEARANCE_SECTION, Integer.valueOf(R.style.TextAppearance_Form_Section));
        //cellConfig.put(CellDescriptor.APPEARANCE_TEXT_LABEL, Integer.valueOf(R.style.TextAppearance_Form_Label));
        //cellConfig.put(CellDescriptor.APPEARANCE_TEXT_VALUE, Integer.valueOf(R.style.TextAppearance_Form_Value));
        //cellConfig.put(CellDescriptor.APPEARANCE_BUTTON, Integer.valueOf(R.style.TextAppearance_Form_Button));

        // color for label and value
        cellConfig.put(CellDescriptor.COLOR_LABEL, Integer.valueOf(0x80C0FFC0));
        cellConfig.put(CellDescriptor.COLOR_VALUE, Integer.valueOf(0xC0C0FFC0));

        // Disabled color for label and value
        cellConfig.put(CellDescriptor.COLOR_LABEL_DISABLED, Integer.valueOf(0x80FFC0C0));
        cellConfig.put(CellDescriptor.COLOR_VALUE_DISABLED, Integer.valueOf(0xC0FFC0C0));

        FormDescriptorAnnotationFactory factory = new FormDescriptorAnnotationFactory(getActivity());
        FormDescriptor descriptor = factory.createFormDescriptorFromAnnotatedClass(entry, cellConfig);

        FormManager formManager = new FormManager();
        formManager.setup(descriptor, mListView, getActivity());
        formManager.setOnFormRowClickListener(this);
        formManager.setOnFormRowValueChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.sample, menu);
        mSaveMenuItem = menu.findItem(R.id.action_save);
    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        updateSaveItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == mSaveMenuItem){
            mChangesMap.clear();
            updateSaveItem();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFormRowClick(FormItemDescriptor itemDescriptor) {

    }

    @Override
    public void onValueChanged(RowDescriptor rowDescriptor, Value<?> oldValue, Value<?> newValue) {
        Log.d(TAG, "Value Changed: " + rowDescriptor.getTitle());
//        Log.d(TAG, "Old Value: "+oldValue);
//        Log.d(TAG, "New Value: "+newValue);

        mChangesMap.put(rowDescriptor.getTag(), newValue);
        updateSaveItem();
    }

    private void updateSaveItem() {
        mSaveMenuItem.setVisible(mChangesMap.size()>0);
    }
}
