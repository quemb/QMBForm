package com.quemb.qmbform.sample.controller;

import android.app.Activity;
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
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.sample.R;

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

        // More styles and colors for cells
        HashMap<String, Object> cellConfig = new HashMap<>(4);

        // Disabled color for label and value
        cellConfig.put(CellDescriptor.COLOR_LABEL_DISABLED, Integer.valueOf(0x80FFC0C0));
        cellConfig.put(CellDescriptor.COLOR_VALUE_DISABLED, Integer.valueOf(0xC0FFC0C0));

        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setCellConfig(cellConfig);

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("section","Text Inputs");
        descriptor.addSection(sectionDescriptor);

        final RowDescriptor rd1 =
                RowDescriptor.newInstance("detail", RowDescriptor.FormRowDescriptorTypeTextInline, "Title", new Value<String>("Detail"));
        sectionDescriptor.addRow(rd1);

        final RowDescriptor rd2 =
                RowDescriptor.newInstance("detail", RowDescriptor.FormRowDescriptorTypeText, "Title", new Value<String>("Detail"));
        sectionDescriptor.addRow(rd2);

        final RowDescriptor rd3 =
                RowDescriptor.newInstance("text", RowDescriptor.FormRowDescriptorTypeText, "Text", new Value<String>("test"));
        sectionDescriptor.addRow(rd3);

        RowDescriptor textDisabled = RowDescriptor.newInstance("textViewDisabled",RowDescriptor.FormRowDescriptorTypeText, "Text Disabled", new Value<String>("test"));
        textDisabled.setDisabled(true);
        sectionDescriptor.addRow(textDisabled);

        final RowDescriptor rd4 =
                RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeURL, "URL", new Value<String>("http://www.github.com/"));
        sectionDescriptor.addRow(rd4);

        RowDescriptor textUrlDisabled = RowDescriptor.newInstance("textViewDisabled",RowDescriptor.FormRowDescriptorTypeURL, "URL Disabled", new Value<String>("http://www.github.com/"));
        textUrlDisabled.setDisabled(true);
        sectionDescriptor.addRow(textUrlDisabled);

        final RowDescriptor rd5 =
                RowDescriptor.newInstance("text", RowDescriptor.FormRowDescriptorTypeEmail, "Email", new Value<String>("support@github.com"));
        sectionDescriptor.addRow(rd5);

        RowDescriptor textEmailDisabled = RowDescriptor.newInstance("textDisabled",RowDescriptor.FormRowDescriptorTypeEmail, "Email Disabled", new Value<String>("support@github.com"));
        textEmailDisabled.setDisabled(true);
        sectionDescriptor.addRow(textEmailDisabled);

        final RowDescriptor rd6 =
                RowDescriptor.newInstance("textView", RowDescriptor.FormRowDescriptorTypeTextView, "Text View", new Value<String>("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et ..."));
        sectionDescriptor.addRow(rd6);

        RowDescriptor textViewDisabled = RowDescriptor.newInstance("textViewDisabled",RowDescriptor.FormRowDescriptorTypeTextView, "Text View Disabled", new Value<String>("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et ..."));
        textViewDisabled.setDisabled(true);
        sectionDescriptor.addRow(textViewDisabled);

        final RowDescriptor rd7 =
                RowDescriptor.newInstance("number", RowDescriptor.FormRowDescriptorTypeNumber, "Number", new Value<Number>(555.456));
        sectionDescriptor.addRow(rd7);

        RowDescriptor numberRowDisabled = RowDescriptor.newInstance("numberDisabled",RowDescriptor.FormRowDescriptorTypeNumber, "Number Disabled", new Value<Number>(555.456));
        numberRowDisabled.setDisabled(true);
        sectionDescriptor.addRow(numberRowDisabled);

        final RowDescriptor integerRow = RowDescriptor.newInstance("integer",RowDescriptor.FormRowDescriptorTypeInteger, "Integer", new Value<Number>(55));
        sectionDescriptor.addRow(integerRow);

        final RowDescriptor integerRowDisabled = RowDescriptor.newInstance("integerDisabled",RowDescriptor.FormRowDescriptorTypeInteger, "Integer Disabled", new Value<Number>(55));
        integerRowDisabled.setDisabled(true);
        sectionDescriptor.addRow(integerRowDisabled);

        final RowDescriptor rd8 =
                RowDescriptor.newInstance("integerSlider", RowDescriptor.FormRowDescriptorTypeIntegerSlider, "Integer Slider", new Value<Integer>(50));
        sectionDescriptor.addRow(rd8);

        RowDescriptor integerSliderDisabled = RowDescriptor.newInstance("integerSliderDisabled",RowDescriptor.FormRowDescriptorTypeIntegerSlider, "Integer Slider Disabled", new Value<Number>(50));
        integerSliderDisabled.setDisabled(true);
        sectionDescriptor.addRow(integerSliderDisabled);

        // --- Picker ---

        SectionDescriptor sectionDescriptor1 = SectionDescriptor.newInstance("sectionOne", "Picker");
        descriptor.addSection(sectionDescriptor1);

        final Activity activity = getActivity();
        RowDescriptor pickerDescriptor = RowDescriptor.newInstance("picker",RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog, "Picker", new Value<String>("Item 5"));
        pickerDescriptor.setDataSource(new DataSource()
        {
            @Override
            public void loadData(final DataSourceListener listener)
            {
                // Can be async
                CustomTask task = new CustomTask(activity);
                task.execute(listener);
            }
        });
        sectionDescriptor1.addRow(pickerDescriptor);

        RowDescriptor pickerDisabledDescriptor = RowDescriptor.newInstance("pickerDisabled",RowDescriptor.FormRowDescriptorTypeSelectorPickerDialog, "Picker Disabled", new Value<String>("Value"));
        pickerDisabledDescriptor.setDisabled(true);
        sectionDescriptor1.addRow(pickerDisabledDescriptor);

        // --- Boolean ---

        SectionDescriptor sectionDescriptor2 = SectionDescriptor.newInstance("sectionTwo","Boolean Inputs");
        descriptor.addSection(sectionDescriptor2);

        final RowDescriptor rd9 =
                RowDescriptor.newInstance("boolean", RowDescriptor.FormRowDescriptorTypeBooleanSwitch, "Boolean Switch", new Value<Boolean>(true));
        sectionDescriptor2.addRow(rd9);

        RowDescriptor booleanDisabled = RowDescriptor.newInstance("booleanDisabled",RowDescriptor.FormRowDescriptorTypeBooleanSwitch, "Boolean Switch Disabled", new Value<Boolean>(true));
        booleanDisabled.setDisabled(true);
        sectionDescriptor2.addRow(booleanDisabled);

        final RowDescriptor rd10 =
                RowDescriptor.newInstance("check", RowDescriptor.FormRowDescriptorTypeBooleanCheck, "Check", new Value<Boolean>(true));
        sectionDescriptor2.addRow(rd10);

        RowDescriptor checkDisabled = RowDescriptor.newInstance("checkDisabled",RowDescriptor.FormRowDescriptorTypeBooleanCheck, "Check Disabled", new Value<Boolean>(true)) ;
        checkDisabled.setDisabled(true);
        sectionDescriptor2.addRow(checkDisabled);

        SectionDescriptor sectionDescriptor3 = SectionDescriptor.newInstance("sectionThree","Button");
        descriptor.addSection(sectionDescriptor3);

        final RowDescriptor button = RowDescriptor.newInstance("button",RowDescriptor.FormRowDescriptorTypeButton, "Tap Me");
        button.setOnFormRowClickListener(new OnFormRowClickListener()
        {
            @Override
            public void onFormRowClick(FormItemDescriptor itemDescriptor)
            {
//                You need to call updateRows in order to update titles
//                itemDescriptor.setTitle("New Title");
//                mFormManager.updateRows();

                integerRow.getValue().setValue(100);
                integerRow.setDisabled(true);

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                builder.setTitle("Tapped");
                builder.show();
            }
        });
        sectionDescriptor3.addRow(button);

        RowDescriptor buttonDisabled = RowDescriptor.newInstance("buttonDisabled",RowDescriptor.FormRowDescriptorTypeButton, "Tap Me Disabled");
        buttonDisabled.setOnFormRowClickListener(new OnFormRowClickListener()
        {
            @Override
            public void onFormRowClick(FormItemDescriptor itemDescriptor)
            {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                builder.setTitle("Tapped");
                builder.show();
            }
        });
        buttonDisabled.setDisabled(true);
        sectionDescriptor3.addRow(buttonDisabled);

        final RowDescriptor rd11 =
                RowDescriptor.newInstance("external", RowDescriptor.FormRowDescriptorTypeExternal, "github.com", new Value<String>("http://github.com"));
        sectionDescriptor3.addRow(rd11);

        SectionDescriptor sectionDescriptor4 = SectionDescriptor.newInstance("sectionFour","Dates");
        descriptor.addSection(sectionDescriptor4);

        final RowDescriptor rd12 =
                RowDescriptor.newInstance("dateInline", RowDescriptor.FormRowDescriptorTypeDateInline, "Date Inline", new Value<Date>(new Date()));
        sectionDescriptor4.addRow(rd12);

        RowDescriptor dateInlineDisabled = RowDescriptor.newInstance("dateInlineDisabled",RowDescriptor.FormRowDescriptorTypeDateInline, "Date Inline Disabled", new Value<Date>(new Date()) );
        dateInlineDisabled.setDisabled(true);
        sectionDescriptor4.addRow(dateInlineDisabled);

        final RowDescriptor rd13 =
                RowDescriptor.newInstance("dateDialog", RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog", null);
        sectionDescriptor4.addRow(rd13);

        RowDescriptor dateDialogDisabled = RowDescriptor.newInstance("dateDialogDisabled",RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog Disabled", null );
        dateDialogDisabled.setDisabled(true);
        sectionDescriptor4.addRow(dateDialogDisabled);

        final RowDescriptor rd14 =
                RowDescriptor.newInstance("timeInline", RowDescriptor.FormRowDescriptorTypeTimeInline, "Time Inline", new Value<Date>(new Date()));
        sectionDescriptor4.addRow(rd14);

        RowDescriptor timeInlineDisabled = RowDescriptor.newInstance("timeInlineDisabled",RowDescriptor.FormRowDescriptorTypeTimeInline, "Time Inline Disabled", new Value<Date>(new Date()) );
        timeInlineDisabled.setDisabled(true);
        sectionDescriptor4.addRow(timeInlineDisabled);

        final RowDescriptor rd15 =
                RowDescriptor.newInstance("timeDialog", RowDescriptor.FormRowDescriptorTypeTime, "Time Dialog", new Value<Date>(new Date()));
        sectionDescriptor4.addRow(rd15);

        RowDescriptor timeDialogDisabled = RowDescriptor.newInstance("timeDialogDisabled", RowDescriptor.FormRowDescriptorTypeTime, "Time Dialog Disabled", new Value<Date>(new Date()) );
        timeDialogDisabled.setDisabled(true);
        sectionDescriptor4.addRow(timeDialogDisabled);

        mFormManager = new FormManager();
        mFormManager.setup(descriptor, mListView, getActivity());
        mFormManager.setOnFormRowClickListener(this);
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

        private Activity mActivity;

        private DataSourceListener mListener;
        private ProgressDialog mProgressDialog;

        public CustomTask(Activity activity)
        {
            super();
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(mActivity, "Loading",
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
