package com.quemb.qmbform.descriptor;

import java.util.List;

/**
 * Created by tonimoeckel on 22.07.14.
 */
public interface DataSourceListener<T> {

    public void onDataSourceLoaded(List<T> list);

}
