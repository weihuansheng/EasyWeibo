package com.microwei.easyweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.view.fragment.ShowLargeImgFragment;

import java.util.ArrayList;

public class ShowLageImgActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager showLargeImgViewPager;
    private TextView topPointTextView;

    private int largePicSize;
    private int pointPosition;
    private Intent intent;

    public static final String KEY_LARGE_PIC_URLS = "keyLargePicUrls";
    public static final String KEY_LARGE_PIC_URLS_POSITION = "keyLargePicUrlsPosition";

    private ArrayList<ShowLargeImgFragment> showLargeImgFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lage_img);
        intent = getIntent();
        if (intent != null) {
            initView();
            initActivity();
            showView();
        }
    }

    private void initView() {
        showLargeImgViewPager = (ViewPager) findViewById(R.id.showLargeImgViewPager);
        topPointTextView = (TextView) findViewById(R.id.topPointTextView);
        showLargeImgViewPager.setOnPageChangeListener(this);
    }

    private void initActivity() {
        ArrayList<String> largePicUrls = intent.getStringArrayListExtra(KEY_LARGE_PIC_URLS);
        pointPosition = intent.getIntExtra(KEY_LARGE_PIC_URLS_POSITION, 0);
        showLargeImgFragments = new ArrayList<>();
        largePicSize = largePicUrls.size();
        for (int i = 0; i < largePicSize; i++) {
            ShowLargeImgFragment showLargeImgFragment = new ShowLargeImgFragment();
            showLargeImgFragment.setLargePicUrl(largePicUrls.get(i));
            showLargeImgFragments.add(showLargeImgFragment);
        }
    }

    private void showView() {
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return showLargeImgFragments.get(position);
            }

            @Override
            public int getCount() {
                return largePicSize;
            }
        };
        showLargeImgViewPager.setAdapter(mAdapter);
        showLargeImgViewPager.setCurrentItem(pointPosition);
        if (pointPosition == 0) {
            StringBuilder topPointText = new StringBuilder();
            topPointText.append(1)
                    .append("/")
                    .append(largePicSize);
            topPointTextView.setText(topPointText);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //do nothing
    }

    @Override
    public void onPageSelected(int position) {
        StringBuilder topPointText = new StringBuilder();
        topPointText.append(position + 1)
                .append("/")
                .append(largePicSize);
        topPointTextView.setText(topPointText);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // do nothing
    }
}
