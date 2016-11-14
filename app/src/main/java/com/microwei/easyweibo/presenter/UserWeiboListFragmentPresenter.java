package com.microwei.easyweibo.presenter;

import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.model.net.Request;
import com.microwei.easyweibo.model.net.UserWeiboListRequest;
import com.microwei.easyweibo.view.fragment.ListFragment;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class UserWeiboListFragmentPresenter extends AbsListFragmentPresenter<WeiboContentEntity>{
    private ListFragment<WeiboContentEntity> mListFragment;
    public UserWeiboListFragmentPresenter(ListFragment<WeiboContentEntity> listFragment){
        mListFragment=listFragment;
    }
    @Override
    protected ListFragment<WeiboContentEntity> getListFragment() {
        return mListFragment;
    }

    @Override
    protected Request getRequest() {
        return new UserWeiboListRequest();
    }

    @Override
    protected Class<WeiboContentEntity> getEntityClass() {
        return WeiboContentEntity.class;
    }
}
