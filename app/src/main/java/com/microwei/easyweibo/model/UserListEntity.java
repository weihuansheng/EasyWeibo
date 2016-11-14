package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-25 .
 */
public class UserListEntity implements Parcelable {
    public ArrayList<UserEntity> mUserEntityList =new ArrayList<>();
    public static UserListEntity parse(String jsonString){
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
    public static UserListEntity parse(JSONObject jsonObject){
        if(jsonObject==null){
            return null;
        }
        UserListEntity users=new UserListEntity();
        JSONArray jsonArray=jsonObject.optJSONArray("users");
        if(jsonArray!=null){
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObjectUserInfo=jsonArray.optJSONObject(i);
                users.mUserEntityList.add(UserEntity.parse(jsonObjectUserInfo));
            }
        }
        return users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mUserEntityList);
    }

    public UserListEntity() {
    }

    protected UserListEntity(Parcel in) {
        this.mUserEntityList = in.createTypedArrayList(UserEntity.CREATOR);
    }

    public static final Parcelable.Creator<UserListEntity> CREATOR = new Parcelable.Creator<UserListEntity>() {
        @Override
        public UserListEntity createFromParcel(Parcel source) {
            return new UserListEntity(source);
        }

        @Override
        public UserListEntity[] newArray(int size) {
            return new UserListEntity[size];
        }
    };
}
