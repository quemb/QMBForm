package com.quemb.qmbform.sample;

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.adapter.FormAdapter;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import java.util.HashMap;


public class SampleActivity extends Activity implements OnFormRowValueChangedListener,
        OnFormRowClickListener{

    public static String TAG = "SampleActivity";

    private ListView mListView;
    private HashMap<String, Value<?>> mChangesMap;
    private MenuItem mSaveMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mChangesMap = new HashMap<String, Value<?>>();
        mListView = (ListView) findViewById(R.id.list);


        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setOnFormRowValueChangedListener(this);

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("sectionOne","Text Inputs");
        descriptor.addSection(sectionDescriptor);

        sectionDescriptor.addRow( RowDescriptor.newInstance("detail",RowDescriptor.FormRowDescriptorTypeName, "Title", new Value<String>("Detail")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeText, "Text", new Value<String>("test")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeURL, "URL", new Value<String>("http://www.github.com/")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeEmail, "Email", new Value<String>("support@github.com")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("textView",RowDescriptor.FormRowDescriptorTypeTextView, "Text View", new Value<String>("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et ...")) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("number",RowDescriptor.FormRowDescriptorTypeNumber, "Number", new Value<Number>(555.456)) );
        sectionDescriptor.addRow( RowDescriptor.newInstance("integer",RowDescriptor.FormRowDescriptorTypeInteger, "Integer", new Value<Number>(55)) );

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
                AlertDialog.Builder builder = new AlertDialog.Builder(SampleActivity.this);
                builder.setTitle("Tapped");
                builder.show();
            }
        });
        sectionDescriptor3.addRow( button );

        SectionDescriptor sectionDescriptor4 = SectionDescriptor.newInstance("sectionFour","Dates");
        descriptor.addSection(sectionDescriptor4);

        sectionDescriptor4.addRow( RowDescriptor.newInstance("dateInline",RowDescriptor.FormRowDescriptorTypeDateInline, "Date Inline", new Value<Date>(new Date()) ));
        sectionDescriptor4.addRow( RowDescriptor.newInstance("dateDialog",RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog") );
        sectionDescriptor4.addRow( RowDescriptor.newInstance("timeInline",RowDescriptor.FormRowDescriptorTypeTimeInline, "Time Inline") );
        sectionDescriptor4.addRow( RowDescriptor.newInstance("timeDialog",RowDescriptor.FormRowDescriptorTypeTime, "Time Dialog", new Value<Date>(new Date())) );

        FormManager formManager = new FormManager();
        formManager.setup(descriptor, mListView, this);
        formManager.setOnFormRowClickListener(this);

    }

    @Override
    public void onValueChanged(RowDescriptor rowDescriptor, Value<?> oldValue, Value<?> newValue) {

        Log.d(TAG, "Value Changed: "+rowDescriptor.getTitle());
//        Log.d(TAG, "Old Value: "+oldValue);
//        Log.d(TAG, "New Value: "+newValue);

        mChangesMap.put(rowDescriptor.getTag(), newValue);
        updateSaveItem();

    }

    private void updateSaveItem() {
        mSaveMenuItem.setVisible(mChangesMap.size()>0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample, menu);
        mSaveMenuItem = menu.findItem(R.id.action_save);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        updateSaveItem();
        return super.onPrepareOptionsMenu(menu);
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
}
