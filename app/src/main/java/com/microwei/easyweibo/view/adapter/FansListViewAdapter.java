package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.MyApplication;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.view.customview.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-05-25 .
 */
public class FansListViewAdapter extends AbsListViewAdapter<UserEntity> {
    private ArrayList<UserEntity> fansInfoList;

    private static class ViewHolder {
        SimpleDraweeView fansProfileImg;
        TextView fansName;
        Button attentionButton;
    }

    public FansListViewAdapter() {
        fansInfoList = new ArrayList<>();
    }


    @Override
    public void addListEntity(List<UserEntity> listEntity) {
        fansInfoList.addAll(listEntity);
    }

    @Override
    public void clearListEntity() {
        fansInfoList.clear();
    }

    @Override
    public void setListViewOnItemListener(CustomListView.BaseListViewOnItemClickListener listener) {

    }

    @Override
    public int getCount() {
        return fansInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return fansInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.fans_listview_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.fansProfileImg = (SimpleDraweeView) convertView.findViewById(R.id.fansProfileImg);
            mViewHolder.fansName = (TextView) convertView.findViewById(R.id.fansName);
            mViewHolder.attentionButton = (Button) convertView.findViewById(R.id.attentionButton);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        UserEntity fansInfo = fansInfoList.get(position);
        mViewHolder.fansProfileImg.setImageURI(fansInfo.profile_image_url);
        mViewHolder.fansName.setText(fansInfo.screen_name);
        if (fansInfo.following) {
            mViewHolder.attentionButton.setText("取消关注");
        } else {
            mViewHolder.attentionButton.setText("加关注");
        }
        return convertView;
    }
}
