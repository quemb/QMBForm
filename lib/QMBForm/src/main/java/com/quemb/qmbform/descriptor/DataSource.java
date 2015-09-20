package com.quemb.qmbform.descriptor;

/**
 * Created by tonimoeckel on 22.07.14.
 */
public abstract class DataSource<T> {
    /**
     * Call the listener for callback actions
     */
    public abstract void loadData(DataSourceListener listener);
}
