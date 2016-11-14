package com.microwei.easyweibo.view.fragment;

import android.content.Context;
import android.content.Intent;

import com.microwei.easyweibo.constants.MyKey;
import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.view.activity.SendCommentActivity;
import com.microwei.easyweibo.view.activity.PersonalHomePageActivity;
import com.microwei.easyweibo.view.activity.ShowLageImgActivity;
import com.microwei.easyweibo.view.activity.WeiboDetaiActivity;
import com.microwei.easyweibo.view.customview.ListViewOnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class WeiboListViewOnItemClickListenerImpl implements ListViewOnItemClickListener.WeiboListViewOnItemClickListener{
    private Context mContext;
    public WeiboListViewOnItemClickListenerImpl(Context context){
        mContext=context;
    }
    @Override
    public void usOnclick(long uId) {
        Intent intent=new Intent(mContext, PersonalHomePageActivity.class);
        intent.putExtra(MyKey.KEY_UID,uId);
        mContext.startActivity(intent);
    }

    @Override
    public void weiboContentOnclick(long weiboId, ArrayList<WeiboContentEntity> jsonStringArrayList, int weiboPosition) {
        Intent intent=new Intent(mContext, WeiboDetaiActivity.class);
        intent.putExtra(MyKey.KEY_ID,weiboId);
        intent.putParcelableArrayListExtra(MyKey.KEY_JSON_STRING_ARRAY_LIST, jsonStringArrayList);
        intent.putExtra(MyKey.KEY_POSITION,weiboPosition);
        mContext.startActivity(intent);
    }

    @Override
    public void weiboImgOnclick(ArrayList<String> largePicUrls, int imgPosition) {
        Intent intent = new Intent(mContext, ShowLageImgActivity.class);
        intent.putStringArrayListExtra(ShowLageImgActivity.KEY_LARGE_PIC_URLS, largePicUrls);
        intent.putExtra(ShowLageImgActivity.KEY_LARGE_PIC_URLS_POSITION, imgPosition);
        mContext.startActivity(intent);
    }

    @Override
    public void shareOnclick(long weiboId) {

    }

    @Override
    public void commentOnclick(long weiboId) {
        Intent intent=new Intent(mContext, SendCommentActivity.class);
        intent.putExtra(MyKey.KEY_WEIBO_ID,weiboId);
        mContext.startActivity(intent);
    }

    @Override
    public void favoriteOnclick(long weiboId) {

    }
}
