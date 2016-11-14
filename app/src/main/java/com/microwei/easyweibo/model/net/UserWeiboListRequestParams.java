package com.microwei.easyweibo.model.net;

import com.microwei.easyweibo.constants.RequestUrl;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class UserWeiboListRequestParams extends RequestParams{
    private String url= RequestUrl.USER_WEIBO_CONTENT_LIST_URL;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
