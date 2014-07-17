package com.quemb.qmbform.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tonimoeckel on 17.07.14.
 */
public class SamplePageAdapter  extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public SamplePageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return "Form - "+position;
    }
}