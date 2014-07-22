package com.quemb.qmbform.descriptor;

import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 22.07.14.
 */
public abstract class DataSource<T> {
    /**
     * Call the listener for callback actions
     */
    public abstract void loadData(DataSourceListener listener);
}
