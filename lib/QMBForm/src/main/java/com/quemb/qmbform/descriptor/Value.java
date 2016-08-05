package com.quemb.qmbform.descriptor;

import android.util.Pair;

/**
 * Created by tonimoeckel on 14.07.14.
 */
public class Value<T> {
    private T mData;
    private OnValueChangeListener<T> mOnValueChangeListener;

    public Value(T data) {
        mData = data;
    }

    public void setData(T data) {
        mData = data;
        if (mOnValueChangeListener != null) {
            mOnValueChangeListener.onChange(data);
        }
    }

    /**
     * Return the embedded data. In case of a Pair{T:key, String:label}, return the key part.
     */
    public T getData()
    {
        if (mData != null && mData instanceof Pair)
        {
            @SuppressWarnings("unchecked") T key = (T) ((Pair)mData).first;
            return key;
        }
        return mData;
    }

    /**
     * In case of a Pair{T:key, String:label}, return the label part.
     */
    public String getPairLabel()
    {
        if (mData != null && mData instanceof Pair)
            return (String) ((Pair)mData).second;
        return null;
    }


    public void setOnValueChangeListener(OnValueChangeListener<T> listener) {
        this.mOnValueChangeListener = listener;
    }
	
    public OnValueChangeListener getOnValueChangeListener() {
        return this.mOnValueChangeListener;
    }	
}
