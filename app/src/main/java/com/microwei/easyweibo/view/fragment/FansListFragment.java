package com.microwei.easyweibo.view.fragment;

import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.model.net.FansListRequestParams;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.presenter.FansListFragmentPresenter;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.FansListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class FansListFragment extends AbsListFragment<UserEntity>{
    @Override
    protected ListFragmentPresenter getListFragmentPresenter() {
        return new FansListFragmentPresenter(this);
    }

    @Override
    protected RequestParams getRequestParams() {
        return new FansListRequestParams();
    }

    @Override
    protected AbsListViewAdapter<UserEntity> getListViewAdapter() {
        return new FansListViewAdapter();
    }

    @Override
    protected CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener() {
        return null;
    }
}
