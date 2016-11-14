package com.microwei.easyweibo.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.constants.MyAuthoInfo;
import com.microwei.easyweibo.model.MySharedPrefence;
import com.microwei.easyweibo.view.fragment.PublicWeiboListFragment;
import com.microwei.easyweibo.view.fragment.FriendWeiboListFragment;
import com.microwei.easyweibo.view.fragment.PersonFragment;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;

    private static final int TAB_BUTTON_CHECK = 1;
    private static final int TAB_BUTTON_UNCHECK = 0;
    private View tabHomePage;
    private View tabPerson;
    private View tabSearch;
    private ImageView imgHomePage;
    private ImageView imgPerson;
    private ImageView imgSearch;
    private TextView textHomePage;
    private TextView textPerson;
    private TextView textSearch;

    private TextView topTextView;
    private TextView topButtonSearch;
    private TextView topButtonSendWeibo;

    private FragmentManager fragmentManager;
    private Fragment homePageFragment;
    private Fragment personFragment;
    private Fragment discoverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        login();
        initActivity();
        initView();
        updateTabBk(TAB_BUTTON_CHECK, TAB_BUTTON_UNCHECK, TAB_BUTTON_UNCHECK);
        fragmentManager.beginTransaction().replace(R.id.activityMainFrame, homePageFragment)
                .commit();
    }

    private void initActivity() {
        fragmentManager = getFragmentManager();
        homePageFragment = new FriendWeiboListFragment();
        discoverFragment = new PublicWeiboListFragment();
        personFragment = new PersonFragment();
    }

    private void login() {
        if (!isLogin()) {
            AuthInfo mAuthInfo = new AuthInfo(MainActivity.this, MyAuthoInfo.APP_KEY,
                    MyAuthoInfo.REDIRECT_URL, null);
            mSsoHandler = new SsoHandler(MainActivity.this, mAuthInfo);
            //启动登录界面
            mSsoHandler.authorize(new AuthListener());
        }
    }

    private boolean isLogin() {
        MyAuthoInfo.access_token = MySharedPrefence.getAccessToken();
        MyAuthoInfo.uid = MySharedPrefence.getUId();
        return MyAuthoInfo.access_token != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private void initView() {
        tabHomePage = findViewById(R.id.tabHomePage);
        tabPerson = findViewById(R.id.tabPerson);
        tabSearch = findViewById(R.id.tabSearch);
        imgHomePage = (ImageView) findViewById(R.id.imgHomePage);
        imgPerson = (ImageView) findViewById(R.id.imgPerson);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        textHomePage = (TextView) findViewById(R.id.textHomePage);
        textPerson = (TextView) findViewById(R.id.textPerson);
        textSearch = (TextView) findViewById(R.id.textSearch);

        topTextView = (TextView) findViewById(R.id.topTextView);
        topButtonSearch = (TextView) findViewById(R.id.topButtonSearch);
        topButtonSendWeibo = (TextView) findViewById(R.id.topButtonSendWeibo);

        tabHomePage.setOnClickListener(this);
        tabPerson.setOnClickListener(this);
        tabSearch.setOnClickListener(this);
        topButtonSearch.setOnClickListener(this);
        topButtonSendWeibo.setOnClickListener(this);
    }

    private void updateTabBk(int buttonHomePageFlag, int buttonShopCarFlag, int buttonPersonFlag) {
        if (buttonHomePageFlag == TAB_BUTTON_CHECK) {
            imgHomePage.setBackgroundResource(R.drawable.main_tab_home_page_selected);
            textHomePage.setTextColor(getResources().getColor(R.color.color_yellow_dark));
        } else {
            imgHomePage.setBackgroundResource(R.drawable.main_tab_home_page);
            textHomePage.setTextColor(Color.GRAY);
        }
        if (buttonShopCarFlag == TAB_BUTTON_CHECK) {
            imgPerson.setBackgroundResource(R.drawable.main_tab_person_selected);
            textPerson.setTextColor(getResources().getColor(R.color.color_yellow_dark));
        } else {
            imgPerson.setBackgroundResource(R.drawable.main_tab_person);
            textPerson.setTextColor(Color.GRAY);
        }
        if (buttonPersonFlag == TAB_BUTTON_CHECK) {
            imgSearch.setBackgroundResource(R.drawable.main_tab_search_selected);
            textSearch.setTextColor(getResources().getColor(R.color.color_yellow_dark));
        } else {
            imgSearch.setBackgroundResource(R.drawable.main_tab_search);
            textSearch.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tabHomePage:
                fragmentManager.beginTransaction().replace(R.id.activityMainFrame, homePageFragment)
                        .commit();
                updateTabBk(TAB_BUTTON_CHECK, TAB_BUTTON_UNCHECK, TAB_BUTTON_UNCHECK);
                topTextView.setVisibility(View.VISIBLE);
                topTextView.setText("首页");
                topButtonSearch.setVisibility(View.GONE);
                topButtonSendWeibo.setVisibility(View.VISIBLE);
                break;
            case R.id.tabPerson:
                fragmentManager.beginTransaction()
                        .replace(R.id.activityMainFrame, personFragment)
                        .commit();
                updateTabBk(TAB_BUTTON_UNCHECK, TAB_BUTTON_CHECK, TAB_BUTTON_UNCHECK);
                topTextView.setVisibility(View.VISIBLE);
                topTextView.setText("我的");
                topButtonSearch.setVisibility(View.GONE);
                topButtonSendWeibo.setVisibility(View.GONE);
                break;
            case R.id.tabSearch:
                fragmentManager.beginTransaction()
                        .replace(R.id.activityMainFrame, discoverFragment)
                        .commit();
                updateTabBk(TAB_BUTTON_UNCHECK, TAB_BUTTON_UNCHECK, TAB_BUTTON_CHECK);
                topTextView.setVisibility(View.GONE);
                topButtonSearch.setVisibility(View.VISIBLE);
                topButtonSendWeibo.setVisibility(View.GONE);
                break;
            case R.id.topButtonSearch:
                Intent intentStartSearchInfoActitity = new Intent(MainActivity.this, SearchInfoActivity.class);
                startActivity(intentStartSearchInfoActitity);
                break;
            case R.id.topButtonSendWeibo:
                Intent intentStartSendWeiboActivity = new Intent(MainActivity.this, SendWeiboActivity.class);
                startActivity(intentStartSendWeiboActivity);
                break;
        }
    }

    private static class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            MyAuthoInfo.mAccessToken = Oauth2AccessToken.parseAccessToken(values); // 从 Bundle 中解析 Token
            MyAuthoInfo.access_token = MyAuthoInfo.mAccessToken.getToken();
            MyAuthoInfo.uid = MyAuthoInfo.mAccessToken.getUid();
            MySharedPrefence.put(MyAuthoInfo.access_token, MyAuthoInfo.uid);
            if (MyAuthoInfo.mAccessToken.isSessionValid()) {
                Log.d(TAG," AccessToken is valid");
            } else {
                // 当您注册的应用程序签名不正确时，就会收到错误Code，请确保签名正确
                String code = values.getString("code");
                Log.d(TAG, "Your key is error and the error code is "+code);
            }
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "WeiboAuthListener is cancel");
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.d(TAG, "WeiboException: "+e);
        }
    }
}
