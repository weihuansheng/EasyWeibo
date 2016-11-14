package com.microwei.easyweibo.view.fragment;

import com.microwei.easyweibo.model.CommentEntity;
import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.model.net.CommentListRequestParams;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.presenter.WeiboDetailFragmentPresenter;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.CommentListViewAdapter;
import com.microwei.easyweibo.view.adapter.WeiboListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class WeiboDetailFragment extends AbsListFragment<CommentEntity>{
    private long weiboId;
    private int weiboPosition;
    private ArrayList<WeiboContentEntity> mWeiboContentEntityArrayList;
    public void setWeiboId(long weiboId){
        this.weiboId=weiboId;
    }
    public void setWeiboPosition(int weiboPosition){
        this.weiboPosition=weiboPosition;
    }
    public void setWeiboContentEntityArrayList(ArrayList<WeiboContentEntity> weiboContentEntityArrayList) {
        mWeiboContentEntityArrayList = weiboContentEntityArrayList;
    }

    @Override
    protected ListFragmentPresenter getListFragmentPresenter() {
        return new WeiboDetailFragmentPresenter(this);
    }

    @Override
    protected RequestParams getRequestParams() {
        CommentListRequestParams commentListRequestParams=new CommentListRequestParams();
        commentListRequestParams.setId(weiboId);
        return commentListRequestParams;
    }

    @Override
    protected AbsListViewAdapter<CommentEntity> getListViewAdapter() {
        return new CommentListViewAdapter();
    }

    @Override
    protected CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener() {
        return null;
    }

    @Override
    protected void initListView() {
        AbsListViewAdapter<WeiboContentEntity> weiboListViewAdapter=new WeiboListViewAdapter();
        weiboListViewAdapter.addListEntity(mWeiboContentEntityArrayList);
        mListView.addHeaderView(weiboListViewAdapter.getView(weiboPosition,null,null));
    }
}
