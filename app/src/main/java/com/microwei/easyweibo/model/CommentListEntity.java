package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-08 .
 */
public class CommentListEntity implements Parcelable {
    public ArrayList<CommentEntity> mCommentEntityList =new ArrayList<>();
    public static CommentListEntity parse(String jsonString){
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
    public static CommentListEntity parse(JSONObject jsonObject){
        if(jsonObject==null){
            return null;
        }
        CommentListEntity comments=new CommentListEntity();
        JSONArray jsonArray=jsonObject.optJSONArray("comments");
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject commentJsonObject=jsonArray.getJSONObject(i);
                comments.mCommentEntityList.add(CommentEntity.parse(commentJsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mCommentEntityList);
    }

    public CommentListEntity() {
    }

    protected CommentListEntity(Parcel in) {
        this.mCommentEntityList = in.createTypedArrayList(CommentEntity.CREATOR);
    }

    public static final Parcelable.Creator<CommentListEntity> CREATOR = new Parcelable.Creator<CommentListEntity>() {
        @Override
        public CommentListEntity createFromParcel(Parcel source) {
            return new CommentListEntity(source);
        }

        @Override
        public CommentListEntity[] newArray(int size) {
            return new CommentListEntity[size];
        }
    };
}
