package com.quemb.qmbform.sample.controller;

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.descriptor.DataSource;
import com.quemb.qmbform.descriptor.DataSourceListener;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.sample.R;

import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tonimoeckel on 17.07.14.
 */
public class SampleFormFragment extends Fragment implements OnFormRowValueChangedListener,
        OnFormRowClickListener{

    private ListView mListView;
    private HashMap<String, Value<?>> mChangesMap;
    private MenuItem mSaveMenuItem;

    public static String TAG = "SampleFormFragment";
    private FormManager mFormManager;

    public static final SampleFormFragment newInstance()
    {
        SampleFormFragment f = new SampleFormFragment();

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
        descriptor.setOnFormRowValueChangedListener(this);

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("section","Text Inputs");
        descriptor.addSection(sectionDescriptor);

        sectionDescriptor.addRow( RowDescriptor.newInstance("detail", RowDescriptor.FormRowDescriptorTypeName, "Title",new Value<String>("Detail")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("detail", RowDescriptor.FormRowDescriptorTypeNameVertical, "Title",new Value<String>("Detail")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeText, "Text", new Value<String>("test")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeURL, "URL", new Value<String>("http://www.github.com/")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeEmail, "Email", new Value<String>("support@github.com")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("textView",RowDescriptor.FormRowDescriptorTypeTextView, "Text View", new Value<String>("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et ...")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("number",RowDescriptor.FormRowDescriptorTypeNumber, "Number", new Value<Number>(555.456)) );

        final RowDescriptor integerRow = RowDescriptor.newInstance("integer",RowDescriptor.FormRowDescriptorTypeInteger, "Integer", new Value<Number>(55));
        sectionDescriptor.addRow( integerRow );
        sectionDescriptor.addRow( RowDescriptor.newInstance("integerSlider",RowDescriptor.FormRowDescriptorTypeIntegerSlider, "Integer Slider", new Value<Integer>(50)) );

        SectionDescriptor sectionDescriptor1 = SectionDescriptor.newInstance("sectionOne","Picker");
        RowDescriptor pickerDescriptor = RowDescriptor.newInstance("picker",RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog, "Picker", new Value<String>("Item 5"));
        pickerDescriptor.setDataSource(new DataSource() {

            @Override
            public void loadData(final DataSourceListener listener) {
                // Can be async
                CustomTask task = new CustomTask();
                task.execute(listener);


            }
        });
        sectionDescriptor1.addRow( pickerDescriptor );
        descriptor.addSection(sectionDescriptor1);

        SectionDescriptor sectionDescriptor2 = SectionDescriptor.newInstance("sectionTwo","Boolean Inputs");
        descriptor.addSection(sectionDescriptor2);

        sectionDescriptor2.addRow( RowDescriptor.newInstance("boolean",RowDescriptor.FormRowDescriptorTypeBooleanSwitch, "Boolean Switch", new Value<Boolean>(true)) );
        sectionDescriptor2.addRow( RowDescriptor.newInstance("check",RowDescriptor.FormRowDescriptorTypeBooleanCheck, "Check", new Value<Boolean>(true)) );

        SectionDescriptor sectionDescriptor3 = SectionDescriptor.newInstance("sectionThree","Button");
        descriptor.addSection(sectionDescriptor3);

        RowDescriptor button = RowDescriptor.newInstance("button",RowDescriptor.FormRowDescriptorTypeButton, "Tap Me");
        button.setOnFormRowClickListener(new OnFormRowClickListener() {
            @Override
            public void onFormRowClick(FormItemDescriptor itemDescriptor) {

//                You need to call updateRows in order to update titles
//                itemDescriptor.setTitle("New Title");
//                mFormManager.updateRows();

                integerRow.getValue().setValue(100);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Tapped");
                builder.show();
            }
        });
        sectionDescriptor3.addRow( button );

        SectionDescriptor sectionDescriptor4 = SectionDescriptor.newInstance("sectionFour","Dates");
        descriptor.addSection(sectionDescriptor4);

        sectionDescriptor4.addRow( RowDescriptor.newInstance("dateInline",RowDescriptor.FormRowDescriptorTypeDateInline, "Date Inline", new Value<Date>(new Date()) ));
        sectionDescriptor4.addRow( RowDescriptor.newInstance("dateDialog",RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog") );
        sectionDescriptor4.addRow( RowDescriptor.newInstance("timeInline",RowDescriptor.FormRowDescriptorTypeTimeInline, "Time Inline" , new Value<Date>(new Date())) );
        sectionDescriptor4.addRow( RowDescriptor.newInstance("timeDialog",RowDescriptor.FormRowDescriptorTypeTime, "Time Dialog", new Value<Date>(new Date())) );

        mFormManager = new FormManager();
        mFormManager.setup(descriptor, mListView, getActivity());
        mFormManager.setOnFormRowClickListener(this);



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
//
        mChangesMap.put(rowDescriptor.getTag(), newValue);
        updateSaveItem();


    }

    private void updateSaveItem() {
        if (mSaveMenuItem != null){
            mSaveMenuItem.setVisible(mChangesMap.size()>0);
        }
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
