package com.quemb.qmbform.sample.controller;

import com.quemb.qmbform.sample.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
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

        fList.add(SampleAnnotationFormFragment.newInstance());
        fList.add(SampleFormFragment.newInstance());
        fList.add(SampleFormFragment.newInstance());

        return fList;
    }


}
