package com.microwei.easyweibo.presenter;

import com.microwei.easyweibo.model.CommentEntity;
import com.microwei.easyweibo.model.net.CommentListRequest;
import com.microwei.easyweibo.model.net.Request;
import com.microwei.easyweibo.view.fragment.ListFragment;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class WeiboDetailFragmentPresenter extends AbsListFragmentPresenter<CommentEntity>{
    private ListFragment<CommentEntity> mListFragment;
    public WeiboDetailFragmentPresenter(ListFragment<CommentEntity> listFragment){
        mListFragment=listFragment;
    }
    @Override
    protected ListFragment<CommentEntity> getListFragment() {
        return mListFragment;
    }

    @Override
    protected Request getRequest() {
        return new CommentListRequest();
    }

    @Override
    protected Class<CommentEntity> getEntityClass() {
        return CommentEntity.class;
    }
}
