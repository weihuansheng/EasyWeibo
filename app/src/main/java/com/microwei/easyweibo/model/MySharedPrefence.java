package com.microwei.easyweibo.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.microwei.easyweibo.MyApplication;

/**
 * Created by Administrator on 2016-04-04 .
 */
public class MySharedPrefence {
    public static void put(String access_token, String uid) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("access_token", access_token);
        editor.putString("uid", uid);
        editor.apply();
    }

    public static String getAccessToken() {
        return getSharedPreferences().getString("access_token", null);
    }

    public static String getUId() {
        return getSharedPreferences().getString("uid", null);
    }

    private static SharedPreferences getSharedPreferences() {
        return (MyApplication.getmContext())
                .getSharedPreferences("mySecret", Context.MODE_PRIVATE);
    }
}
