package com.microwei.easyweibo.util;

import com.microwei.easyweibo.MyApplication;

/**
 * Created by Administrator on 2016-04-30 .
 */
public class GetScreenDimenUtil {
    public static int getScreenWidth() {
        return MyApplication.getmContext().getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(){
        return MyApplication.getmContext().getResources().getDisplayMetrics().heightPixels;
    }
}

