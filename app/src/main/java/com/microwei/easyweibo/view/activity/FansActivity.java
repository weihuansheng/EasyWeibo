package com.microwei.easyweibo.view.activity;

import android.os.Bundle;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.view.fragment.FansListFragment;

public class FansActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_fans);
        setHeadTextView("我的粉丝");
        getFragmentManager().beginTransaction()
                .replace(R.id.activityFansFrame,new FansListFragment())
                .commit();
    }
}
