package com.microwei.easyweibo.view.activity;

import android.os.Bundle;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.view.fragment.AttentionsListFragment;

public class AttentionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_attentions);
        setHeadTextView("我关注的人");
        getFragmentManager().beginTransaction()
                .replace(R.id.activityAttentionsFrame,new AttentionsListFragment())
                .commit();
    }
}
