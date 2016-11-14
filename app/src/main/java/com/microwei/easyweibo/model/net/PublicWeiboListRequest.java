package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class PublicWeiboListRequest implements Request{
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof PublicWeiboListRequestParams){
            PublicWeiboListRequestParams publicWeiboListRequestParams=(PublicWeiboListRequestParams)mRequestParams;
            url=url.append(publicWeiboListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(publicWeiboListRequestParams.getAccessToken())
                    .append("&")
                    .append("count=")
                    .append(publicWeiboListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(publicWeiboListRequestParams.getPage());

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
