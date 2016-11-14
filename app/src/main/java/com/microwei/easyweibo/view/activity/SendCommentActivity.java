package com.microwei.easyweibo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microwei.easyweibo.R;
import com.microwei.easyweibo.constants.MyKey;

public class SendCommentActivity extends BaseActivity{

    private EditText editTextWriteComment;
    private Button buttonSendComment;
    private long weiboId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeadTextView("发评论");
        setContentLayout(R.layout.activity_add_comment);
        Intent intent=getIntent();
        if(intent!=null){
            init();
            weiboId=intent.getLongExtra(MyKey.KEY_WEIBO_ID,0);
        }
    }
    private void init(){
        editTextWriteComment=(EditText)findViewById(R.id.editTextWriteComment);
        buttonSendComment=(Button)findViewById(R.id.buttonSendComment);
        buttonSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
    }
    private void sendComment(){

    }
}
