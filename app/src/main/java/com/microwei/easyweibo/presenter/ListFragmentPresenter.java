package com.microwei.easyweibo.presenter;

import com.microwei.easyweibo.model.net.RequestParams;

/**
 * Created by Administrator on 2016-11-12 .
 */
public interface ListFragmentPresenter extends BasePresenter{
    void loadList(RequestParams requestParams);
    void loadMoreList(RequestParams requestParams);
}
