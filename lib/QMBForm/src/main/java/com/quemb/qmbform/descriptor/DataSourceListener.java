package com.quemb.qmbform.descriptor;

import java.util.List;

/**
 * Created by tonimoeckel on 22.07.14.
 */
public interface DataSourceListener {

    public <T> void onDataSourceLoaded(List<T> list);

}
