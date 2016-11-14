package com.microwei.easyweibo.model.net;

import com.microwei.easyweibo.constants.MyAuthoInfo;

/**
 * Created by Administrator on 2016-11-12 .
 */
public class RequestParams {
    private String access_token= MyAuthoInfo.access_token;
    private String uid=MyAuthoInfo.uid;
    private static final int DEFAULT_EACH_PAGE_ITEM_COUNT=5;
    private static final int DEFAULT_PAGE_NUM=1;
    private int count=DEFAULT_EACH_PAGE_ITEM_COUNT;
    private int page=DEFAULT_PAGE_NUM;
    public void setAccessToken(String access_token){
        this.access_token=access_token;
    }
    public String getAccessToken(){
        return access_token;
    }
    public void setUId(String uid){
        this.uid=uid;
    }
    public String getUId(){
        return uid;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
