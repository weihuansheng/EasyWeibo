package com.microwei.easyweibo.model.net;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.microwei.easyweibo.MyApplication;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-11 .
 */
public class RequestPoolExecutor {
    private RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getmContext());

    private RequestPoolExecutor() {
    }

    public static RequestPoolExecutor newInstance() {
        return RequestPoolExecutorHolder.sRequestPoolExecutor;
    }

    private static class RequestPoolExecutorHolder {
        private static RequestPoolExecutor sRequestPoolExecutor = new RequestPoolExecutor();
    }

    public void addRequest(Request request, ResponseListener responseListener) {
        if (request != null && responseListener != null) {
            if (request.getRequestMethod() == Request.METHOD_GET)
                addGetRequest(request, responseListener);
            if (request.getRequestMethod() == Request.METHOD_POST)
                addPostRequest(request, responseListener);
        }
    }

    public void addGetRequest(Request getRequest, final ResponseListener responseListener) {
        if (getRequest != null && responseListener != null) {
            StringRequest stringRequest = new StringRequest(getRequest.getActualUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    responseListener.onResponseSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    responseListener.onResponseFail(volleyError);
                }
            });
            requestQueue.add(stringRequest);
        }
    }

    public void addPostRequest(final Request postRequest, final ResponseListener responseListener) {
        if (postRequest != null && responseListener != null) {
            StringRequest stringPostRequest = new StringRequest(com.android.volley.Request.Method.POST, postRequest.getActualUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    responseListener.onResponseSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    responseListener.onResponseFail(volleyError);
                }
            }) {
                @Override
                protected Map getParams() throws AuthFailureError {
                    return postRequest.getMapParams();
                }
            };
            requestQueue.add(stringPostRequest);
        }
    }
}
