package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class FriendWeiboListRequest implements Request {
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof FriendWeiboListRequestParams){
            FriendWeiboListRequestParams friendWeiboListRequestParams=(FriendWeiboListRequestParams)mRequestParams;
            url=url.append(friendWeiboListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(friendWeiboListRequestParams.getAccessToken())
                    .append("&")
                    .append("count=")
                    .append(friendWeiboListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(friendWeiboListRequestParams.getPage());

        }
        return url.toString();
    }

    @Override
    public void setRequestParams(RequestParams requestParams) {
        mRequestParams=requestParams;
    }

    @Override
    public Map getMapParams() {
        return null;
    }
}
