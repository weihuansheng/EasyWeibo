package com.microwei.easyweibo;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016-04-03 .
 */
public class MyApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        Fresco.initialize(mContext);
    }
    public static Context getmContext(){
        return mContext;
    }
}
