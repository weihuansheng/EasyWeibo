package com.microwei.easyweibo.presenter;

import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.model.net.FriendWeiboListRequest;
import com.microwei.easyweibo.model.net.Request;
import com.microwei.easyweibo.view.fragment.ListFragment;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class HomePageFragmentPresenter extends AbsListFragmentPresenter<WeiboContentEntity>{
    private ListFragment<WeiboContentEntity> mListFragment;
    public HomePageFragmentPresenter(ListFragment<WeiboContentEntity> listFragment){
        mListFragment=listFragment;
    }
    @Override
    protected ListFragment<WeiboContentEntity> getListFragment() {
        return mListFragment;
    }

    @Override
    protected Request getRequest() {
        return new FriendWeiboListRequest();
    }

    @Override
    protected Class<WeiboContentEntity> getEntityClass() {
        return WeiboContentEntity.class;
    }
}
