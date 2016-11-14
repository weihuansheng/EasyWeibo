package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016-04-04 .
 */
public class UserEntity implements Parcelable {
    public long id;
    //public String idstr;
    public String screen_name;
    public String location;
    public String description;
    public String url;
    public String profile_image_url;
    public String domain;
    public String gender;
    public int followers_count;
    public int friends_count;
    public int statuses_count;
    public int favourites_count;
    public String created_at;
    public boolean following;
    public boolean follow_me;
    public int online_status;

    public static UserEntity parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserEntity parse(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.id = jsonObject.optLong("id", 0);
        userEntity.screen_name = jsonObject.optString("screen_name", "");
        userEntity.location = jsonObject.optString("location", "");
        userEntity.description = jsonObject.optString("description", "");
        userEntity.url = jsonObject.optString("url", "");
        userEntity.profile_image_url = jsonObject.optString("profile_image_url", "");
        userEntity.domain = jsonObject.optString("domain", "");
        userEntity.gender = jsonObject.optString("gender", "");
        userEntity.followers_count = jsonObject.optInt("followers_count", 0);
        userEntity.friends_count = jsonObject.optInt("friends_count", 0);
        userEntity.statuses_count = jsonObject.optInt("statuses_count", 0);
        userEntity.favourites_count = jsonObject.optInt("favourites_count", 0);
        //JSONObject statusJsonObject=jsonObject.optJSONObject("status");
        userEntity.created_at = jsonObject.optString("created_at", "");
        userEntity.following = jsonObject.optBoolean("following", false);
        userEntity.follow_me = jsonObject.optBoolean("follow_me", false);
        userEntity.online_status = jsonObject.optInt("online_status", 0);
        return userEntity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.screen_name);
        dest.writeString(this.location);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.profile_image_url);
        dest.writeString(this.domain);
        dest.writeString(this.gender);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.friends_count);
        dest.writeInt(this.statuses_count);
        dest.writeInt(this.favourites_count);
        dest.writeString(this.created_at);
        dest.writeByte(this.following ? (byte) 1 : (byte) 0);
        dest.writeByte(this.follow_me ? (byte) 1 : (byte) 0);
        dest.writeInt(this.online_status);
    }

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        this.id = in.readLong();
        this.screen_name = in.readString();
        this.location = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.profile_image_url = in.readString();
        this.domain = in.readString();
        this.gender = in.readString();
        this.followers_count = in.readInt();
        this.friends_count = in.readInt();
        this.statuses_count = in.readInt();
        this.favourites_count = in.readInt();
        this.created_at = in.readString();
        this.following = in.readByte() != 0;
        this.follow_me = in.readByte() != 0;
        this.online_status = in.readInt();
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
