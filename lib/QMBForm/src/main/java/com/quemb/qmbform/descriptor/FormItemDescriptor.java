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
    private HashMap<String, CellConfigObject[]> mCellConfig;
    private HashMap<String, Object> mCustomCellConfig;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
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

    public HashMap<String, CellConfigObject[]> getCellConfig() {
        return mCellConfig;
    }

    public void setCellConfig(HashMap<String, CellConfigObject[]> cellConfig) {
        mCellConfig = cellConfig;
    }

    public HashMap<String, Object> getCustomCellConfig() {
        return mCustomCellConfig;
    }

    public void setCustomCellConfig(HashMap<String, Object> customCellConfig) {
        mCustomCellConfig = customCellConfig;
    }
}
