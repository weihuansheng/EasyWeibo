package com.microwei.easyweibo.view.fragment;

import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.model.net.AttentionsListRequestParams;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.presenter.AttentionsListFragmentPresenter;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.AttentionsListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class AttentionsListFragment extends AbsListFragment<UserEntity>{
    @Override
    protected ListFragmentPresenter getListFragmentPresenter() {
        return new AttentionsListFragmentPresenter(this);
    }

    @Override
    protected RequestParams getRequestParams() {
        return new AttentionsListRequestParams();
    }

    @Override
    protected AbsListViewAdapter<UserEntity> getListViewAdapter() {
        return new AttentionsListViewAdapter();
    }

    @Override
    protected CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener() {
        return null;
    }
}
