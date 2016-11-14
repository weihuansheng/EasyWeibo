package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-09 .
 */
public class WeiboContentListEntity implements Parcelable {
    public ArrayList<WeiboContentEntity> mWeiboContentEntityList =new ArrayList<>();

    public static WeiboContentListEntity parse(String jsonString){
        if(jsonString==null){
            return null;
        }
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            return parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static WeiboContentListEntity parse(JSONObject jsonObject){
        if(jsonObject==null){
            return null;
        }
        WeiboContentListEntity weiboContents=new WeiboContentListEntity();
        JSONArray jsonArray=jsonObject.optJSONArray("statuses");
        if(jsonArray!=null){
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObjectWeiboContent=jsonArray.optJSONObject(i);
                weiboContents.mWeiboContentEntityList.add(WeiboContentEntity.parse(jsonObjectWeiboContent));
            }
        }
        return weiboContents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mWeiboContentEntityList);
    }

    public WeiboContentListEntity() {
    }

    protected WeiboContentListEntity(Parcel in) {
        this.mWeiboContentEntityList = in.createTypedArrayList(WeiboContentEntity.CREATOR);
    }

    public static final Parcelable.Creator<WeiboContentListEntity> CREATOR = new Parcelable.Creator<WeiboContentListEntity>() {
        @Override
        public WeiboContentListEntity createFromParcel(Parcel source) {
            return new WeiboContentListEntity(source);
        }

        @Override
        public WeiboContentListEntity[] newArray(int size) {
            return new WeiboContentListEntity[size];
        }
    };
}
