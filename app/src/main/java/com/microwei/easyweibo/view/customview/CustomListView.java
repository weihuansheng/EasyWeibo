package com.microwei.easyweibo.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;

/**
 * Created by Administrator on 2016-05-08 .
 */
public class CustomListView extends ListView implements AbsListView.OnScrollListener {
    private ArriveBottomListener arriveBottomListener;
    private int tempTotalItemCount = 0;
    private BaseListViewOnItemClickListener mBaseListViewOnItemClickListener;
    private AbsListViewAdapter mBaseListViewAdapter;

    public interface BaseListViewOnItemClickListener {
    }
    public interface ArriveBottomListener {
        void arriveBottom();
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            mBaseListViewAdapter.setScroll(false);
            mBaseListViewAdapter.notifyDataSetChanged();
        } else {
            mBaseListViewAdapter.setScroll(true);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount
                && totalItemCount != tempTotalItemCount && totalItemCount > 1) {
            arriveBottomListener.arriveBottom();
            tempTotalItemCount = totalItemCount;
        }
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setArriveBottomListener(ArriveBottomListener arriveBottomListener) {
        this.arriveBottomListener = arriveBottomListener;
        setOnScrollListener(this);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof AbsListViewAdapter) {
            mBaseListViewAdapter = (AbsListViewAdapter) adapter;
            mBaseListViewAdapter.setListViewOnItemListener(mBaseListViewOnItemClickListener);
        }
    }

    public void setListViewOnItemClickListener(BaseListViewOnItemClickListener listener) {
        mBaseListViewOnItemClickListener = listener;
    }
}

