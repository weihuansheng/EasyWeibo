package com.microwei.easyweibo.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.microwei.easyweibo.MyApplication;

/**
 * Created by Administrator on 2016-04-30 .
 */
public class ImageLoaderFactory {
    private static ImageLoader mImageLoader;
    private ImageLoaderFactory(){
    }
    public static ImageLoader getImageLoader(){
        if(mImageLoader==null){
            RequestQueue requestQueue= Volley.newRequestQueue(MyApplication.getmContext());
            BitmapCache bitmapCache=BitmapCache.getBitmapCache();
            mImageLoader=new ImageLoader(requestQueue,bitmapCache);
        }
        return mImageLoader;
    }
}
