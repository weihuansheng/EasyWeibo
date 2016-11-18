package com.microwei.easyweibo.view;

import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.view.customview.CustomListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-14 .
 */
public interface ListViewOnItemClickListener {
    public interface WeiboListViewOnItemClickListener extends CustomListView.BaseListViewOnItemClickListener {
        void usOnclick(long uId);

        void weiboContentOnclick(long weiboId,ArrayList<WeiboContentEntity> weiboContentEntityList,int weiboPosition);

        void weiboImgOnclick(ArrayList<String> largePicUrls, int imgPosition);

        void shareOnclick(long weiboId);

        void commentOnclick(long weiboId);

        void favoriteOnclick(long weiboId);
    }
    public interface CommentListViewOnItemClickListener extends CustomListView.BaseListViewOnItemClickListener{
    }
    public interface AttentionsListViewOnItemClickListener extends CustomListView.BaseListViewOnItemClickListener{

    }
}