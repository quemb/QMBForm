package com.quemb.qmbform.descriptor;

/**
 * Created by tonimoeckel on 18.02.15.
 */
public interface OnFormRowChangeListener {

    public void onRowAdded(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor);

    public void onRowRemoved(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor);

    public void onRowChanged(RowDescriptor<?> rowDescriptor, SectionDescriptor sectionDescriptor);

}
