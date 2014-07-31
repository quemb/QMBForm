package com.quemb.qmbform.sample.model;

import android.support.v4.app.Fragment;

/**
 * Created by tonimoeckel on 31.07.14.
 */
public class TabItem {

    private String mTitle;
    private Fragment mFragment;

    public TabItem(String title, Fragment fragment){
        mTitle = title;
        mFragment = fragment;
    }


    public Fragment getFragment() {
        return mFragment;
    }

    public String getTitle() {
        return mTitle;
    }
}
