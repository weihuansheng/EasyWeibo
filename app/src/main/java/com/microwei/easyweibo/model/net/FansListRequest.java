package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class FansListRequest implements Request{
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof FansListRequestParams){
            FansListRequestParams fansListRequestParams=(FansListRequestParams)mRequestParams;
            url=url.append(fansListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(fansListRequestParams.getAccessToken())
                    .append("&")
                    .append("uid=")
                    .append(fansListRequestParams.getUId())
                    .append("&")
                    .append("count=")
                    .append(fansListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(fansListRequestParams.getPage());

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
