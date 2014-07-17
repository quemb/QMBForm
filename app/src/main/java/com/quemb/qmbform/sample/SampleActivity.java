package com.quemb.qmbform.sample;

import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class SampleActivity extends FragmentActivity {

    public static String TAG = "SampleActivity";

    SamplePageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_page_view);
        List<Fragment> fragments = getFragments();
        pageAdapter = new SamplePageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(SampleFormFragment.newInstance());
        fList.add(SampleFormFragment.newInstance());
        fList.add(SampleFormFragment.newInstance());

        return fList;
    }


}
