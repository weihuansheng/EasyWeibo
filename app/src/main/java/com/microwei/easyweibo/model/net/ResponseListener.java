package com.microwei.easyweibo.model.net;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016-11-11 .
 */
public interface ResponseListener{
    void onResponseSuccess(String response);
    void onResponseFail(VolleyError volleyError);
}
