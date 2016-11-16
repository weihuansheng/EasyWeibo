package com.microwei.easyweibo.view.fragment;

import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.model.net.UserWeiboListRequestParams;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.presenter.UserWeiboListFragmentPresenter;
import com.microwei.easyweibo.view.WeiboListViewOnItemClickListenerImpl;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.WeiboListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class UserWeiboListFragment extends AbsListFragment<WeiboContentEntity>{
    private long uid;

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    protected ListFragmentPresenter getListFragmentPresenter() {
        return new UserWeiboListFragmentPresenter(this);
    }

    @Override
    protected RequestParams getRequestParams() {
        UserWeiboListRequestParams userWeiboListRequestParams=new UserWeiboListRequestParams();
        userWeiboListRequestParams.setUId(String.valueOf(uid));
        return userWeiboListRequestParams;
    }

    @Override
    protected AbsListViewAdapter<WeiboContentEntity> getListViewAdapter() {
        return new WeiboListViewAdapter();
    }

    @Override
    protected CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener() {
        return new WeiboListViewOnItemClickListenerImpl((getActivity()));
    }
}
