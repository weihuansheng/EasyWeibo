package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.microwei.easyweibo.MyApplication;

/**
 * Created by Administrator on 2016-05-12 .
 */
public class EmptyListViewAdapter extends BaseAdapter {
    private int mEmptyLayoutId;

    public EmptyListViewAdapter(int emptyLayoutId) {
        mEmptyLayoutId = emptyLayoutId;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return LayoutInflater.from(MyApplication.getmContext()).inflate(mEmptyLayoutId, null);

    }

}
