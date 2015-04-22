package com.quemb.qmbform.descriptor;

import java.util.ArrayList;

/**
 * Created by tonimoeckel on 22.07.14.
 */
public interface DataSourceListener<T> {

    public void onDataSourceLoaded(ArrayList<T> list);

}
