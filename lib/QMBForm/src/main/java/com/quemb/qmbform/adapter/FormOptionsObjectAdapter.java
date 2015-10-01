package com.quemb.qmbform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quemb.qmbform.descriptor.FormOptionsObject;

import java.util.ArrayList;

/**
 * Created by pmaccamp on 9/9/2015.
 */
public class FormOptionsObjectAdapter extends ArrayAdapter {
    private ArrayList<FormOptionsObject> mOptions;
    private int mResource;
    private int mDropDownResource;

    public FormOptionsObjectAdapter(Context context, int resource) {
        super(context, resource);
        this.mResource = resource;
    }

    public FormOptionsObjectAdapter(Context context, int resource, ArrayList<FormOptionsObject> options) {
        super(context, resource);
        this.mOptions = options;
        this.mResource = resource;
    }

    public FormOptionsObjectAdapter(Context context, int resource, int dropDownResource, ArrayList<FormOptionsObject> options) {
        super(context, resource);
        this.mOptions = options;
        this.mResource = resource;
        this.mDropDownResource = dropDownResource;
    }

    @Override
    public int getCount() {
        return mOptions.size();
    }

    @Override
    public Object getItem(int position) {
        return mOptions.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public View inflateView(int position, View convertView, ViewGroup parent, int resource) {
        TextView textView = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            textView = (TextView) inflator.inflate(resource, parent, false);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(mOptions.get(position).getDisplayText());
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return inflateView(position, convertView, parent, this.mResource);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return inflateView(position, convertView, parent, this.mDropDownResource);
    }
}
