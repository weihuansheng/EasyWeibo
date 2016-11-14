package com.microwei.easyweibo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.MyApplication;

/**
 * Created by Administrator on 2016-05-25 .
 */
public class PersonListViewAdapter extends BaseAdapter {
    private final String[] TEXT_ARRAY = {"我的微博", "我的粉丝", "我关注的人", "@我的评论"};
    private final int[] IMG_ID_ARRAY = {R.drawable.person_my_weibo, R.drawable.person_my_fans
            , R.drawable.person_my_attention, R.drawable.person_me};

    @Override
    public int getCount() {
        return TEXT_ARRAY.length;
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
        convertView = LayoutInflater.from(MyApplication.getmContext())
                .inflate(R.layout.person_listview_item, null);
        ImageView personListImg = (ImageView) convertView.findViewById(R.id.personListImg);
        personListImg.setImageResource(IMG_ID_ARRAY[position]);
        TextView personListText = (TextView) convertView.findViewById(R.id.personListText);
        personListText.setText(TEXT_ARRAY[position]);
        return convertView;
    }
}
