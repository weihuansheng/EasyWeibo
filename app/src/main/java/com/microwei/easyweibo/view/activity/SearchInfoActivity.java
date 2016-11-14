package com.microwei.easyweibo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.microwei.easyweibo.R;

public class SearchInfoActivity extends AppCompatActivity {

    private EditText editSearchInfo;
    private Button buttonSearchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
        init();
        buttonSearchInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void init(){
        editSearchInfo=(EditText)findViewById(R.id.editSearchInfo);
        buttonSearchInfo=(Button)findViewById(R.id.buttonSearchInfo);

    }
}
