package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class UserWeiboListRequest implements Request{
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof UserWeiboListRequestParams){
            UserWeiboListRequestParams userWeiboListRequestParams=(UserWeiboListRequestParams)mRequestParams;
            url=url.append(userWeiboListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(userWeiboListRequestParams.getAccessToken())
                    .append("&")
                    .append("uid=")
                    .append(userWeiboListRequestParams.getUId())
                    .append("&")
                    .append("count=")
                    .append(userWeiboListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(userWeiboListRequestParams.getPage());

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
