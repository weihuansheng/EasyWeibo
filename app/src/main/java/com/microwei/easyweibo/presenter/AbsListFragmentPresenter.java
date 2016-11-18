package com.microwei.easyweibo.presenter;

import com.android.volley.VolleyError;
import com.microwei.easyweibo.model.CommentEntity;
import com.microwei.easyweibo.model.CommentListEntity;
import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.model.UserListEntity;
import com.microwei.easyweibo.model.WeiboContentListEntity;
import com.microwei.easyweibo.model.net.Request;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.model.net.RequestPoolExecutor;
import com.microwei.easyweibo.model.net.ResponseListener;
import com.microwei.easyweibo.view.fragment.ListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016-11-12 .
 */
public abstract class AbsListFragmentPresenter<T> implements ListFragmentPresenter{
    private ListFragment<T> mListFragment;
    private Request mRequest;
    private Class<T> mEntityClass;
    private RequestPoolExecutor mRequestPoolExecutor;
    @Override
    public void start() {
        mListFragment=getListFragment();
        mRequest=getRequest();
        mEntityClass=getEntityClass();
        mRequestPoolExecutor=RequestPoolExecutor.newInstance();
    }

    @Override
    public void loadList(RequestParams requestParams) {
        mListFragment.showLoading();
        mRequest.setRequestParams(requestParams);
        mRequestPoolExecutor.performRequest(mRequest, new ResponseListener() {
            @Override
            public void onResponseSuccess(String response) {
                List<T> listEntity=castResponseToList(response);
                if(listEntity.isEmpty()){
                    mListFragment.showListIsEmpty();
                }
                else {
                    mListFragment.showList(listEntity);
                }
            }

            @Override
            public void onResponseFail(VolleyError volleyError) {
                mListFragment.showLoadError(volleyError);
            }
        });
    }

    @Override
    public void loadMoreList(RequestParams requestParams ) {
        mRequest.setRequestParams(requestParams);
        RequestPoolExecutor.newInstance().performRequest(mRequest, new ResponseListener() {
            @Override
            public void onResponseSuccess(String response) {
                List<T> listEntity=castResponseToList(response);
                if(listEntity.isEmpty()){
                    mListFragment.showMoreListIsEmpty();
                }
                else {
                    mListFragment.showMoreList(listEntity);
                }
            }

            @Override
            public void onResponseFail(VolleyError volleyError) {
                mListFragment.showLoadeMoreError(volleyError);
            }
        });
    }
    private List<T> castResponseToList(String response){
        List<T> list;
        if(mEntityClass.equals(CommentEntity.class)){
            list=(List<T>) CommentListEntity.parse(response).mCommentEntityList;
        }
        else if (mEntityClass.equals(UserEntity.class)){
            list=(List<T>) UserListEntity.parse(response).mUserEntityList;
        }
        else {
            list=(List<T>) WeiboContentListEntity.parse(response).mWeiboContentEntityList;
        }
        return list;
    }
    @Override
    public void detachView() {
        mListFragment=null;
        mRequestPoolExecutor.cancelAllRequests();
    }
    protected abstract ListFragment<T> getListFragment();
    protected abstract Request getRequest();
    protected abstract Class<T> getEntityClass();
}
