package com.microwei.easyweibo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.MyApplication;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout rootLayout;
    private View buttonReturn;
    private TextView headTextView;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonReturn:
                BaseActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }
    private void initView(){
        rootLayout=(LinearLayout)findViewById(R.id.rootLayout);
        buttonReturn=findViewById(R.id.buttonReturn);
        headTextView=(TextView)findViewById(R.id.headTextView);
        buttonReturn.setOnClickListener(this);
    }
    protected void setHeadTextView(String text){
        headTextView.setText(text);
    }
    protected void setContentLayout(int resId){
        rootLayout.addView(LayoutInflater.from(MyApplication.getmContext()).inflate(resId,null));
    }
}
