package com.microwei.easyweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.constants.MyKey;
import com.microwei.easyweibo.model.WeiboContentEntity;
import com.microwei.easyweibo.view.fragment.WeiboDetailFragment;

import java.util.ArrayList;

public class WeiboDetaiActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeadTextView("微博详情");
        setContentLayout(R.layout.activity_weibo_detai);
        long weiboId;
        ArrayList<WeiboContentEntity> jsonStringArrayList;
        int weiboPosition;
        Intent intent = getIntent();
        if (intent != null) {
            weiboId = intent.getLongExtra(MyKey.KEY_ID, 0);
            jsonStringArrayList = intent.getParcelableArrayListExtra(MyKey.KEY_WEIBO_CONTENT_ENTITY_LIST);
            weiboPosition = intent.getIntExtra(MyKey.KEY_POSITION, 0);
            WeiboDetailFragment weiboDetailFragment = new WeiboDetailFragment();
            weiboDetailFragment.setWeiboId(weiboId);
            weiboDetailFragment.setWeiboPosition(weiboPosition);
            weiboDetailFragment.setWeiboContentEntityArrayList(jsonStringArrayList);
            getFragmentManager().beginTransaction().replace(R.id.activityWeiboDetaiFrame, weiboDetailFragment)
                    .commit();
        }
    }
}
