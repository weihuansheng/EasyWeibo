package com.microwei.easyweibo.presenter;

import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.model.net.AttentionsListRequest;
import com.microwei.easyweibo.model.net.Request;
import com.microwei.easyweibo.view.fragment.ListFragment;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class AttentionsListFragmentPresenter extends AbsListFragmentPresenter<UserEntity>{
    private ListFragment<UserEntity> mListFragment;
    public AttentionsListFragmentPresenter(ListFragment<UserEntity> listFragment){
        this.mListFragment=listFragment;
    }
    @Override
    protected ListFragment<UserEntity> getListFragment() {
        return mListFragment;
    }

    @Override
    protected Request getRequest() {
        return new AttentionsListRequest();
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }
}
