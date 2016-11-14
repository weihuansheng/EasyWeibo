package com.microwei.easyweibo.view.fragment;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Administrator on 2016-11-11 .
 */
public interface ListFragment<T> {
    void showLoading();

    void hideLoading();

    void showList(List<T> list);

    void showListIsEmpty();

    void showLoadError(VolleyError volleyError);

    void showLoadingMore();

    void hideLoadingMore();

    void showMoreList(List<T> list);

    void showMoreListIsEmpty();

    void showLoadeMoreError(VolleyError volleyError);
}
