package com.microwei.easyweibo.view.adapter;

import android.widget.BaseAdapter;

import com.microwei.easyweibo.view.customview.CustomListView;

import java.util.List;

/**
 * Created by Administrator on 2016-11-12 .
 */
public abstract class AbsListViewAdapter<T> extends BaseAdapter {
    private boolean isScroll=false;
    public abstract void addListEntity(List<T> listEntity);
    public abstract void clearListEntity();
    public abstract void setListViewOnItemListener(CustomListView.BaseListViewOnItemClickListener listener);
    public void setScroll(boolean scroll){
        isScroll=scroll;
    }
    protected boolean isScroll(){
        return isScroll;
    }
}
