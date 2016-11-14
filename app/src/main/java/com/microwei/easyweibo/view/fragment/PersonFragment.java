package com.microwei.easyweibo.view.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.microwei.easyweibo.R;
import com.microwei.easyweibo.constants.MyAuthoInfo;
import com.microwei.easyweibo.constants.MyKey;
import com.microwei.easyweibo.view.activity.AttentionsActivity;
import com.microwei.easyweibo.view.activity.FansActivity;
import com.microwei.easyweibo.view.activity.PersonalHomePageActivity;
import com.microwei.easyweibo.view.adapter.PersonListViewAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-05-25 .
 */
public class PersonFragment extends Fragment {
    private static final String TAG="PersonFragment";
    private LayoutInflater mInflater;

    private View mPersonFragmentView;
    private SimpleDraweeView mProfileImg;
    private TextView mProfileName;
    private ListView mPersonListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mPersonFragmentView==null){
            mInflater=inflater;
            initView();
            initListener();
            showProfileImg();
        }
        return mPersonFragmentView;
    }
    private void initView(){
        mPersonFragmentView=mInflater.inflate(R.layout.fragment_person,null);
        mProfileImg=(SimpleDraweeView)mPersonFragmentView.findViewById(R.id.mProfileImg);
        mProfileName=(TextView)mPersonFragmentView.findViewById(R.id.mProfileName);
        mPersonListView=(ListView)mPersonFragmentView.findViewById(R.id.mPersonListView);
        mPersonListView.setAdapter(new PersonListViewAdapter());
    }
    private void initListener(){
        mProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mPersonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(getActivity(), PersonalHomePageActivity.class);
                        intent0.putExtra(MyKey.KEY_UID, MyAuthoInfo.uid);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), FansActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), AttentionsActivity.class);
                        startActivity(intent2);
                        break;
                }

            }
        });
    }
    private void showProfileImg(){
        Map param=new HashMap();
        param.put("access_token", MyAuthoInfo.access_token);
        param.put("uid", MyAuthoInfo.uid);
    }
}
