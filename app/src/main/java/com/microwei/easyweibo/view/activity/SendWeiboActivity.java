package com.microwei.easyweibo.view.activity;

import android.os.Bundle;

import com.microwei.easyweibo.R;

public class SendWeiboActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_send_weibo);
        setHeadTextView("发微博");
    }
}
