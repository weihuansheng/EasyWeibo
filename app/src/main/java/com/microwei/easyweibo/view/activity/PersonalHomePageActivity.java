package com.microwei.easyweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.constants.MyKey;
import com.microwei.easyweibo.view.fragment.UserWeiboListFragment;

public class PersonalHomePageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeadTextView("个人主页");
        setContentLayout(R.layout.activity_personal_home_page);
        Intent intent = getIntent();
        if (intent != null) {
            long uId = intent.getLongExtra(MyKey.KEY_UID, 0);
            UserWeiboListFragment personalHomePageFragment = new UserWeiboListFragment();
            personalHomePageFragment.setUid(uId);
            getFragmentManager().beginTransaction()
                    .replace(R.id.activityPersonalHomePageFrame, personalHomePageFragment)
                    .commit();
        }
    }
}
