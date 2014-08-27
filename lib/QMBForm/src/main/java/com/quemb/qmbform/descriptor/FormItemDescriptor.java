package com.quemb.qmbform.descriptor;

import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.view.Cell;

import java.util.HashMap;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class FormItemDescriptor {

    protected Cell mCell;

    protected String mTag;



    protected String mTitle;
    private OnFormRowClickListener mOnFormRowClickListener;
    private HashMap<String, Object> mCellConfig;


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getTag() {
        return mTag;
    }

    public Cell getCell() {
        return mCell;
    }

    public void setCell(Cell cell) {
        mCell = cell;
    }


    public OnFormRowClickListener getOnFormRowClickListener() {
        return mOnFormRowClickListener;
    }

    public void setOnFormRowClickListener(OnFormRowClickListener onFormRowClickListener) {
        mOnFormRowClickListener = onFormRowClickListener;
    }

    public HashMap<String, Object> getCellConfig() {
        return mCellConfig;
    }

    public void setCellConfig(HashMap<String, Object> cellConfig) {
        mCellConfig = cellConfig;
    }
}
