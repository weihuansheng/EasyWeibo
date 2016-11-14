package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-13 .
 */
public class CommentListRequest implements Request{
    private RequestParams mRequestParams;
    @Override
    public int getRequestMethod() {
        return Request.METHOD_GET;
    }

    @Override
    public String getActualUrl() {
        StringBuilder url=new StringBuilder();
        if(mRequestParams instanceof CommentListRequestParams){
            CommentListRequestParams commentListRequestParams=(CommentListRequestParams)mRequestParams;
            url=url.append(commentListRequestParams.getUrl())
                    .append("?")
                    .append("access_token=")
                    .append(commentListRequestParams.getAccessToken())
                    .append("&")
                    .append("id=")
                    .append(commentListRequestParams.getId())
                    .append("&")
                    .append("count=")
                    .append(commentListRequestParams.getCount())
                    .append("&")
                    .append("page=")
                    .append(commentListRequestParams.getPage());

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
