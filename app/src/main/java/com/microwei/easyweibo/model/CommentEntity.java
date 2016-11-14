package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.microwei.easyweibo.util.FormatDateUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016-05-08 .
 */
public class CommentEntity implements Parcelable {
    public String created_at;
    public String text;
    public String source;
    public UserEntity mUserEntity;
    public static CommentEntity parse(JSONObject jsonObject){
        if(jsonObject==null){
            return null;
        }
        CommentEntity commentEntity =new CommentEntity();
        commentEntity.created_at=FormatDateUtil.formatDate(jsonObject.optString("created_at",""));
        commentEntity.text=jsonObject.optString("text","");
        commentEntity.source=jsonObject.optString("source","");
        commentEntity.mUserEntity = UserEntity.parse(jsonObject.optJSONObject("user"));
        return commentEntity;
    }
    public  static CommentEntity parse(String jsonString){
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.created_at);
        dest.writeString(this.text);
        dest.writeString(this.source);
        dest.writeParcelable(this.mUserEntity, flags);
    }

    public CommentEntity() {
    }

    protected CommentEntity(Parcel in) {
        this.created_at = in.readString();
        this.text = in.readString();
        this.source = in.readString();
        this.mUserEntity = in.readParcelable(UserEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommentEntity> CREATOR = new Parcelable.Creator<CommentEntity>() {
        @Override
        public CommentEntity createFromParcel(Parcel source) {
            return new CommentEntity(source);
        }

        @Override
        public CommentEntity[] newArray(int size) {
            return new CommentEntity[size];
        }
    };
}
