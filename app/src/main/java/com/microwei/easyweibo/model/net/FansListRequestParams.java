package com.microwei.easyweibo.model.net;

import com.microwei.easyweibo.constants.RequestUrl;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class FansListRequestParams extends RequestParams{
    private String url= RequestUrl.FANS_LIST_URL;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
