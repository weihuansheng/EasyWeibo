package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.MyApplication;
import com.microwei.easyweibo.util.GetScreenDimenUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-30 .
 */
public class ImageGridViewAdapter extends BaseAdapter{
    private ArrayList<String> picUrls;
    private int picCount;
    private int mImageViewHeight;

    private static final int MAX_COUNT=9;

    private static class ViewHolder{
        public SimpleDraweeView mImageView;
    }
    public ImageGridViewAdapter(){
    }
    public void setPicUrls(ArrayList<String> picUrls){
        if(picUrls!=null){
            this.picUrls=picUrls;
            picCount=picUrls.size();
            setMImageViewHeight();
        }
    }
    @Override
    public int getCount() {
        if(picCount>=MAX_COUNT){
            return MAX_COUNT;
        }
        return picCount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(MyApplication.getmContext()).inflate(R.layout.image_gridview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.mImageView=(SimpleDraweeView)convertView.findViewById(R.id.mImageView);
            ViewGroup.LayoutParams lp=viewHolder.mImageView.getLayoutParams();
            lp.height=mImageViewHeight;
            lp.width=mImageViewHeight;
            viewHolder.mImageView.setLayoutParams(lp);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.mImageView.setImageURI(picUrls.get(position));
//        if(!isScroll){
//            viewHolder.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageLoader.get(picUrls.get(position), viewHolder.imageListener,mImageViewHeight,mImageViewHeight);
//        }
//        else {
//            viewHolder.mImageView.setScaleType(ImageView.ScaleType.CENTER);
//            viewHolder.mImageView.setImageResource(R.drawable.default_img);
//        }
        return convertView;
    }
    private void setMImageViewHeight(){
       if (picCount==1||picCount==2||picCount==4){
            mImageViewHeight=(int) (GetScreenDimenUtil.getScreenWidth()*0.5f);
        }
        else {
            mImageViewHeight=(int) (GetScreenDimenUtil.getScreenWidth()*0.333333f);
        }
    }
}
