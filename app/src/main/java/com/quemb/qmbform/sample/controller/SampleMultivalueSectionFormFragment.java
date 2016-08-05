package com.quemb.qmbform.sample.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.DataSource;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
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

    public static final SampleMultivalueSectionFormFragment newInstance()
    {
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
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mChangesMap = new HashMap<String, Value<?>>();

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

        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setCellConfig(cellConfig);

        final ArrayList<String> values = new ArrayList<>();
        values.add("red");
        values.add("blue");
        values.add("green");

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("colors","Colors");
        sectionDescriptor.setMultivalueSection(true);

        descriptor.addSection(sectionDescriptor);


        for (String item : values){
            sectionDescriptor.addRow(RowDescriptor.newInstance("colors-"+item, RowDescriptor.FormRowDescriptorTypeText,null,new Value<String>(item)),
                    cellConfig);
        }
        sectionDescriptor.addRow(RowDescriptor.newInstance("new", RowDescriptor.FormRowDescriptorTypeText), cellConfig);


        SectionDescriptor sectionDescriptor2 = SectionDescriptor.newInstance("multiPicker","Pick a color");
        sectionDescriptor2.setMultivalueSection(true);
        descriptor.addSection(sectionDescriptor2);
        RowDescriptor pickerDescriptor2 = RowDescriptor.newInstance("picker2",RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog);
        pickerDescriptor2.setDataSource(new DataSource() {

            @Override
            public void loadData(final DataSourceListener listener) {

                listener.onDataSourceLoaded(values);

            }
        });
        sectionDescriptor2.addRow( pickerDescriptor2, cellConfig);


        SectionDescriptor sectionDescriptor3 = SectionDescriptor.newInstance("multiPicker","Pick a color");
        sectionDescriptor3.setMultivalueSection(true);
        descriptor.addSection(sectionDescriptor3);
        RowDescriptor pickerDescriptor3 = RowDescriptor.newInstance("picker3",RowDescriptor.FormRowDescriptorTypeTextPickerDialog);
        pickerDescriptor3.setDataSource(new DataSource() {

            @Override
            public void loadData(final DataSourceListener listener) {

                listener.onDataSourceLoaded(values);

            }
        });
        sectionDescriptor3.addRow( pickerDescriptor3, cellConfig);


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
    public void onValueChanged(RowDescriptor<?> rowDescriptor, Value<?> oldValue, Value<?> newValue) {

        Log.d(TAG, "Value Changed: " + rowDescriptor.getTitle());
//
        mChangesMap.put(rowDescriptor.getTag(), newValue);
        updateSaveItem();


    }

    private void updateSaveItem() {
        if (mSaveMenuItem != null){
            mSaveMenuItem.setVisible(mChangesMap.size()>0);
        }
    }

    @Override
    public void onRowAdded(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowRemoved(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowChanged(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    private class CustomTask extends AsyncTask<DataSourceListener, Void, ArrayList<String>> {

        private DataSourceListener mListener;
        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(getActivity(), "Loading",
                    "Do some work", true);
        }

        protected ArrayList<String> doInBackground(DataSourceListener... listeners) {

            mListener = (DataSourceListener)listeners[0];

            ArrayList<String> items = new ArrayList<String>();
            for (Integer i=0;i<10;i++){
                doFakeWork();
                items.add("Item "+String.valueOf(i));
            }

            return items;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            mProgressDialog.dismiss();
            mListener.onDataSourceLoaded(strings);
        }

        private void doFakeWork() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
