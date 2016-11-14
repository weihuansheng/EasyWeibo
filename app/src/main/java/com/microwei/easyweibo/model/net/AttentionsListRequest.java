package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class AttentionsListRequest implements Request{
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof AttentionsListRequestParams){
            AttentionsListRequestParams attentionsListRequestParams=(AttentionsListRequestParams)mRequestParams;
            url=url.append(attentionsListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(attentionsListRequestParams.getAccessToken())
                    .append("&")
                    .append("uid=")
                    .append(attentionsListRequestParams.getUId())
                    .append("&")
                    .append("count=")
                    .append(attentionsListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(attentionsListRequestParams.getPage());

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
