package com.microwei.easyweibo.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.model.net.RequestParams;
import com.microwei.easyweibo.presenter.ListFragmentPresenter;
import com.microwei.easyweibo.view.adapter.AbsListViewAdapter;
import com.microwei.easyweibo.view.adapter.EmptyListViewAdapter;
import com.microwei.easyweibo.view.customview.CustomListView;
import com.microwei.easyweibo.view.customview.CustomPullDownRefresh;

import java.util.List;

/**
 * Created by Administrator on 2016-11-12 .
 */
public abstract class AbsListFragment<T> extends Fragment implements ListFragment<T>,CustomPullDownRefresh.PullDownRefreshListener,CustomListView.ArriveBottomListener {
    private LayoutInflater mInflater;
    private AbsListViewAdapter<T> mListViewAdapter;
    private ListFragmentPresenter mListFragmentPresenter;
    private RequestParams mRequestParams;
    private CustomListView.BaseListViewOnItemClickListener mListViewOnItemClickListener;

    private View mRootView;
    private CustomPullDownRefresh mCustomPullDownRefreshView;
    protected CustomListView mListView;
    private View loadingView;
//    private View listIsEmptyView;
    private View loadErrorView;

    private View loadingMoreView;
    private ProgressBar loadingMoreProgressBar;
    private TextView loadingMoreTextView;

    private boolean canArriveBottom = false;
    private boolean isPullDownRefresh=false;

    private int page=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListFragmentPresenter=getListFragmentPresenter();
        mRequestParams=getRequestParams();
        mListViewOnItemClickListener=getListViewOnItemClickListener();
        mListViewAdapter=getListViewAdapter();
        mListFragmentPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mInflater = inflater;
            initView();
            mListFragmentPresenter.loadList(mRequestParams);
        }
        return mRootView;
    }

    private void initView() {
        mRootView = mInflater.inflate(R.layout.fragment_base_pull_down_refresh, null);
        mCustomPullDownRefreshView = (CustomPullDownRefresh) mRootView.findViewById(R.id.fragmentHomePagecustomPullDownRefresh);
        mListView = (CustomListView) mRootView.findViewById(R.id.mCustomBaseListView);
        loadingView = mRootView.findViewById(R.id.internetLoadingView);
        loadErrorView = mRootView.findViewById(R.id.internetFailView);

        loadingMoreView = mInflater.inflate(R.layout.internet_loading_layout, null);
        loadingMoreProgressBar = (ProgressBar) loadingMoreView.findViewById(R.id.internetLoadingProgressBar);
        loadingMoreTextView = (TextView) loadingMoreView.findViewById(R.id.internetLoadingTextView);

        mCustomPullDownRefreshView.setPullDownRefreshListener(this);
        initListView();
        mListView.setListViewOnItemClickListener(mListViewOnItemClickListener);
        mListView.setArriveBottomListener(this);
    }

    @Override
    public void showLoading() {
        if(!isPullDownRefresh) {
            mListView.setVisibility(View.GONE);
            loadErrorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        mListView.setVisibility(View.VISIBLE);
        loadErrorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showList(List<T> list) {
        mCustomPullDownRefreshView.startHidePullDownHeadAnim();
        hideLoading();
        mListViewAdapter.clearListEntity();
        mListViewAdapter.addListEntity(list);
        if (mListViewAdapter.getCount() >= 5) {
            if (mListView.getFooterViewsCount() == 0) {
                mListView.addFooterView(loadingMoreView);
                canArriveBottom = true;
            }
        }
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void showListIsEmpty() {
        hideLoading();
        mListView.setAdapter(new EmptyListViewAdapter(getEmptyLayoutId()));
    }

    @Override
    public void showLoadError(VolleyError volleyError) {
        mCustomPullDownRefreshView.startHidePullDownHeadAnim();
        mListView.setVisibility(View.GONE);
        loadErrorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingMore() {
        // do nothing
    }

    @Override
    public void hideLoadingMore() {
        // do nothing
    }

    @Override
    public void showMoreList(List<T> list) {
        mListViewAdapter.addListEntity(list);
        mListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreListIsEmpty() {
        loadingMoreProgressBar.setVisibility(View.GONE);
        loadingMoreTextView.setText("没有更多了");
    }

    @Override
    public void showLoadeMoreError(VolleyError volleyError) {
        loadingMoreProgressBar.setVisibility(View.GONE);
        loadingMoreTextView.setText("加载失败");
    }
    protected void initListView(){
        //do nothing
    }
    protected int getEmptyLayoutId(){
        return R.layout.empty_contens_layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListFragmentPresenter.detachView();
    }

    protected abstract ListFragmentPresenter getListFragmentPresenter();
    protected abstract RequestParams getRequestParams();
    protected abstract AbsListViewAdapter<T> getListViewAdapter();
    protected abstract CustomListView.BaseListViewOnItemClickListener getListViewOnItemClickListener();

    @Override
    public void arriveBottom() {
        if (canArriveBottom) {
            mRequestParams.setPage(++page);
            mListFragmentPresenter.loadMoreList(mRequestParams);
        }
    }

    @Override
    public void nowRefresh() {
        isPullDownRefresh=true;
        page=1;
        mRequestParams.setPage(page);
        mListFragmentPresenter.loadList(mRequestParams);
    }
}
