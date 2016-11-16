package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.MyApplication;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.view.customview.CustomListView;
import com.microwei.easyweibo.view.ListViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class WeiboListViewAdapter extends AbsListViewAdapter<WeiboContentEntity>{
    private ArrayList<WeiboContentEntity> mWeiboContentEntityArrayList;
    private ViewHolder viewHolder;

    private ListViewOnItemClickListener.WeiboListViewOnItemClickListener weiboListViewOnItemClickListener;

    public WeiboListViewAdapter() {
        mWeiboContentEntityArrayList = new ArrayList<>();
    }

    @Override
    public void addListEntity(List<WeiboContentEntity> listEntity) {
        mWeiboContentEntityArrayList.addAll(listEntity);
    }

    @Override
    public void clearListEntity() {
        mWeiboContentEntityArrayList.clear();
    }

    @Override
    public void setListViewOnItemListener(CustomListView.BaseListViewOnItemClickListener listener) {
        if(listener instanceof ListViewOnItemClickListener.WeiboListViewOnItemClickListener) {
            weiboListViewOnItemClickListener = (ListViewOnItemClickListener.WeiboListViewOnItemClickListener) listener;
        }
    }
    private static class ViewHolder {
        public View rootView;
        public SimpleDraweeView profileImg;
        public TextView usName;
        public TextView createdAtTime;
        public TextView weiboText;
        public View customLine;
        public TextView retweetedWeiboText;
        public GridView mImageGridView;
        public TextView shareNum;
        public TextView commentNum;
        public TextView favoriteNum;
        public ImageGridViewAdapter mImageGridViewAdpter;
    }

    @Override
    public int getCount() {
        return mWeiboContentEntityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeiboContentEntityArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.weibo_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.rootView=convertView.findViewById(R.id.rootView);
            viewHolder.profileImg = (SimpleDraweeView) convertView.findViewById(R.id.profileImg);
            viewHolder.usName = (TextView) convertView.findViewById(R.id.usName);
            viewHolder.createdAtTime = (TextView) convertView.findViewById(R.id.createdAtTime);
            viewHolder.weiboText = (TextView) convertView.findViewById(R.id.weiboText);
            viewHolder.customLine = convertView.findViewById(R.id.customLine);
            viewHolder.retweetedWeiboText = (TextView) convertView.findViewById(R.id.retweetedWeiboText);
            viewHolder.mImageGridView = (GridView) convertView.findViewById(R.id.mGridView);
            viewHolder.shareNum = (TextView) convertView.findViewById(R.id.shareNum);
            viewHolder.commentNum = (TextView) convertView.findViewById(R.id.commentNum);
            viewHolder.favoriteNum = (TextView) convertView.findViewById(R.id.favoriteNum);
            viewHolder.mImageGridViewAdpter = new ImageGridViewAdapter();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final WeiboContentEntity weiboContentEntity = mWeiboContentEntityArrayList.get(position);
        WeiboContentEntity retweetedWeibo = weiboContentEntity.retweeted_status;
        final UserEntity userEntity = weiboContentEntity.user;
        viewHolder.profileImg.setImageURI(userEntity.profile_image_url);
        viewHolder.usName.setText(userEntity.screen_name);
        viewHolder.createdAtTime.setText(weiboContentEntity.created_at);
        viewHolder.weiboText.setText(weiboContentEntity.text);
        final ArrayList<String> picUrls = new ArrayList<>();
        weiboContentEntity.pic_urls= weiboContentEntity.middle_pic_urls;
        picUrls.addAll(weiboContentEntity.pic_urls);
        final ArrayList<String> largePicUrls = new ArrayList<>();
        largePicUrls.addAll(weiboContentEntity.large_pic_urls);
        if (retweetedWeibo != null) {
            viewHolder.customLine.setVisibility(View.VISIBLE);
            viewHolder.retweetedWeiboText.setVisibility(View.VISIBLE);
            viewHolder.retweetedWeiboText.setText(retweetedWeibo.text);
            picUrls.addAll(retweetedWeibo.pic_urls);
            largePicUrls.addAll(retweetedWeibo.large_pic_urls);
        } else {
            viewHolder.customLine.setVisibility(View.GONE);
            viewHolder.retweetedWeiboText.setVisibility(View.GONE);
        }
        if(!isScroll()){
            viewHolder.mImageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int imgPosition, long id) {
                    weiboListViewOnItemClickListener.weiboImgOnclick(largePicUrls, imgPosition);
                }
            });
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.rootView:
                            weiboListViewOnItemClickListener.weiboContentOnclick(weiboContentEntity.id,mWeiboContentEntityArrayList,position);
                            break;
                        case R.id.profileImg:
                            weiboListViewOnItemClickListener.usOnclick(userEntity.id);
                            break;
                        case R.id.shareNum:
                            break;
                        case R.id.commentNum:
                            weiboListViewOnItemClickListener.commentOnclick(weiboContentEntity.id);
                            break;
                        case R.id.favoriteNum:
                            break;
                    }
                }
            };
            viewHolder.rootView.setOnClickListener(onClickListener);
            viewHolder.profileImg.setOnClickListener(onClickListener);
//            viewHolder.weiboText.setOnClickListener(onClickListener);
//            viewHolder.retweetedWeiboText.setOnClickListener(onClickListener);
            viewHolder.shareNum.setOnClickListener(onClickListener);
            viewHolder.commentNum.setOnClickListener(onClickListener);
            viewHolder.favoriteNum.setOnClickListener(onClickListener);
        }
        showPics(picUrls);
        viewHolder.shareNum.setText(String.valueOf(weiboContentEntity.reposts_count));
        viewHolder.commentNum.setText(String.valueOf(weiboContentEntity.comments_count));
        viewHolder.favoriteNum.setText(String.valueOf(weiboContentEntity.attitudes_count));
        return convertView;
    }

    private void showPics(ArrayList<String> picUrls) {
        GridView mGridView = viewHolder.mImageGridView;
        int picCount = picUrls.size();
        if (picCount == 0) {
            mGridView.setVisibility(View.GONE);
        } else {
            mGridView.setVisibility(View.VISIBLE);
            if (picCount == 1) {
                mGridView.setNumColumns(1);
            } else if (picCount == 2 || picCount == 4) {
                mGridView.setNumColumns(2);
            } else {
                mGridView.setNumColumns(3);
            }
            viewHolder.mImageGridViewAdpter.setPicUrls(picUrls);
            mGridView.setAdapter(viewHolder.mImageGridViewAdpter);
        }
    }
}
