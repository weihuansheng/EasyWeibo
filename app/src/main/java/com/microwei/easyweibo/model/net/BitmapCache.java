package com.microwei.easyweibo.model.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2016-05-07 .
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private static BitmapCache bitmapCache;
    private LruCache<String, Bitmap> mCache;

    private BitmapCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    public static BitmapCache getBitmapCache(){
        if(bitmapCache==null){
            bitmapCache=new BitmapCache();
        }
        return bitmapCache;
    }
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }

}
