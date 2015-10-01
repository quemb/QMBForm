package com.quemb.qmbform.sample.controller;

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

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.FormOptionsObject;
import com.quemb.qmbform.descriptor.OnFormRowChangeListener;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.sample.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 17.07.14.
 */
public class SampleMultivalueSectionFormFragment extends Fragment implements OnFormRowValueChangedListener,
        OnFormRowClickListener, OnFormRowChangeListener {

    private ListView mListView;
    private HashMap<String, Value<?>> mChangesMap;
    private MenuItem mSaveMenuItem;

    public static String TAG = "SampleFormFragment";
    private FormManager mFormManager;

    public static final SampleMultivalueSectionFormFragment newInstance() {
        SampleMultivalueSectionFormFragment f = new SampleMultivalueSectionFormFragment();

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

        mChangesMap = new HashMap<String, Value<?>>();

        FormDescriptor descriptor = FormDescriptor.newInstance();

        final ArrayList<String> values = new ArrayList<>();
        values.add("red");
        values.add("blue");
        values.add("green");

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("colors", "Colors");
        sectionDescriptor.setMultivalueSection(true);

        descriptor.addSection(sectionDescriptor);


        for (String item : values) {
            sectionDescriptor.addRow(RowDescriptor.newInstance("colors-" + item, RowDescriptor.FormRowDescriptorTypeText, null, new Value<String>(item)));
        }
        sectionDescriptor.addRow(RowDescriptor.newInstance("new", RowDescriptor.FormRowDescriptorTypeText));


        SectionDescriptor sectionDescriptor2 = SectionDescriptor.newInstance("multiPicker", "Pick a color");
        sectionDescriptor2.setMultivalueSection(true);
        descriptor.addSection(sectionDescriptor2);
        RowDescriptor pickerDescriptor2 = RowDescriptor.newInstance("picker2",
                RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog);

        ArrayList<FormOptionsObject> selectorOptions = new ArrayList<>();
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("red", "Red"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("blue", "Blue"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("green", "Green"));

        pickerDescriptor2.setSelectorOptions(selectorOptions);
        sectionDescriptor2.addRow(pickerDescriptor2);

        SectionDescriptor sectionDescriptor3 = SectionDescriptor.newInstance("multiPicker", "Pick a color");
        sectionDescriptor3.setMultivalueSection(true);
        descriptor.addSection(sectionDescriptor3);
        RowDescriptor pickerDescriptor3 = RowDescriptor.newInstance("picker3",
                RowDescriptor.FormRowDescriptorTypeSelectorTextPickerDialogInline);

        selectorOptions = new ArrayList<>();
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("red", "Red"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("blue", "Blue"));
        selectorOptions.add(FormOptionsObject.createFormOptionsObject("green", "Green"));

        pickerDescriptor3.setSelectorOptions(selectorOptions);
        pickerDescriptor3.setDisabled(false);
        sectionDescriptor3.addRow(pickerDescriptor3);


        mFormManager = new FormManager();
        mFormManager.setup(descriptor, mListView, getActivity());
        mFormManager.setOnFormRowClickListener(this);
        mFormManager.setOnFormRowChangeListener(this);
        mFormManager.setOnFormRowValueChangedListener(this);


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
        if (item == mSaveMenuItem) {
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
//
        mChangesMap.put(rowDescriptor.getTag(), newValue);
        updateSaveItem();
        mFormManager.updateRows();
    }

    private void updateSaveItem() {
        if (mSaveMenuItem != null) {
            mSaveMenuItem.setVisible(mChangesMap.size() > 0);
        }
    }

    @Override
    public void onRowAdded(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowRemoved(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowChanged(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

}
