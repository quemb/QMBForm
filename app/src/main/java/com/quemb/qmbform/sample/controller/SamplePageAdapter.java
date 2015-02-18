package com.quemb.qmbform.sample.controller;

import com.quemb.qmbform.sample.model.TabItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tonimoeckel on 17.07.14.
 */
public class SamplePageAdapter  extends FragmentPagerAdapter {
    private List<TabItem> mTabItems;

    public SamplePageAdapter(FragmentManager fm, List<TabItem> tabItems) {
        super(fm);
        mTabItems = tabItems;
    }
    @Override
    public Fragment getItem(int position) {
        return mTabItems.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mTabItems.size();
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return mTabItems.get(position).getTitle();
    }


}