package com.microwei.easyweibo.model.net;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-11 .
 */
public interface Request {
    int METHOD_GET=0;
    int METHOD_POST=1;
    int getRequestMethod();
    String getActualUrl();
    void setRequestParams(RequestParams requestParams);
    Map getMapParams();
}
