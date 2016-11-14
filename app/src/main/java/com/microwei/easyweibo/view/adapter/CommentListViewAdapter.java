package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.MyApplication;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.model.CommentEntity;
import com.microwei.easyweibo.model.UserEntity;
import com.microwei.easyweibo.view.customview.CustomListView;
import com.microwei.easyweibo.view.customview.ListViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-05-08 .
 */
public class CommentListViewAdapter extends AbsListViewAdapter<CommentEntity>{
    private ArrayList<CommentEntity> mCommentEntityArrayList;
    private ListViewOnItemClickListener.CommentListViewOnItemClickListener commentListViewOnItemClickListner;

    private static class ViewHolder{
        public SimpleDraweeView commentUsImg;
        public TextView commentUsName;
        public TextView commentCreatedAtTime;
        public TextView commentText;
    }
    public CommentListViewAdapter(){
        mCommentEntityArrayList =new ArrayList<>();
    }

    @Override
    public void addListEntity(List<CommentEntity> listEntity) {
        mCommentEntityArrayList.addAll(listEntity);
    }

    @Override
    public void clearListEntity() {
        mCommentEntityArrayList.clear();
    }

    @Override
    public void setListViewOnItemListener(CustomListView.BaseListViewOnItemClickListener listener) {
        if(listener instanceof ListViewOnItemClickListener.CommentListViewOnItemClickListener){
            commentListViewOnItemClickListner=(ListViewOnItemClickListener.CommentListViewOnItemClickListener)listener;
        }
    }

    @Override
    public int getCount() {
        return mCommentEntityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentEntityArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(MyApplication.getmContext())
                    .inflate(R.layout.comment_listview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.commentUsImg=(SimpleDraweeView)convertView.findViewById(R.id.commentUsImg);
            viewHolder.commentUsName=(TextView)convertView.findViewById(R.id.commentUsName);
            viewHolder.commentCreatedAtTime=(TextView)convertView.findViewById(R.id.commentCreatedAtTime);
            viewHolder.commentText=(TextView)convertView.findViewById(R.id.commentText);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CommentEntity commentEntity = mCommentEntityArrayList.get(position);
        UserEntity userEntity = commentEntity.mUserEntity;
        viewHolder.commentUsImg.setImageURI(userEntity.profile_image_url);
        viewHolder.commentUsName.setText(userEntity.screen_name);
        viewHolder.commentCreatedAtTime.setText(commentEntity.created_at);
        viewHolder.commentText.setText(commentEntity.text);
        return convertView;
    }
}
