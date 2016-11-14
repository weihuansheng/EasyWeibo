package com.microwei.easyweibo.view.fragment;

import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.model.net.PublicWeiboListRequestParams;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.presenter.PublicWeiboListFragmentPresenter;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.WeiboListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class PublicWeiboListFragment extends AbsListFragment<WeiboContentEntity>{
    @Override
    protected ListFragmentPresenter getListFragmentPresenter() {
        return new PublicWeiboListFragmentPresenter(this);
    }

    @Override
    protected RequestParams getRequestParams() {
        return new PublicWeiboListRequestParams();
    }

    @Override
    protected AbsListViewAdapter<WeiboContentEntity> getListViewAdapter() {
        return new WeiboListViewAdapter();
    }

    @Override
    protected CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener() {
        return new WeiboListViewOnItemClickListenerImpl(getActivity());
    }
}
