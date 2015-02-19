package com.quemb.qmbform.sample.controller;

import com.quemb.qmbform.sample.R;
import com.quemb.qmbform.sample.model.TabItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;


public class SampleActivity extends ActionBarActivity {

    public static String TAG = "SampleActivity";

    SamplePageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page_view);
        List<TabItem> fragments = getTabItems();
        pageAdapter = new SamplePageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(pageAdapter);

    }

    private List<TabItem> getTabItems() {
        List<TabItem> fList = new ArrayList<TabItem>();

        fList.add(new TabItem("Annotation",SampleAnnotationFormFragment.newInstance()));
        fList.add(new TabItem("Multi Value Section", SampleMultivalueSectionFormFragment.newInstance()));
        fList.add(new TabItem("Manual", SampleFormFragment.newInstance()));

        return fList;
    }




}
