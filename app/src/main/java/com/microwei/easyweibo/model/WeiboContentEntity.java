package com.microwei.easyweibo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.microwei.easyweibo.util.FormatDateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-04-09 .
 * 微博内容
 */
public class WeiboContentEntity implements Parcelable {
    public String created_at;
    public long id;
    public String text;
    public String source;
    public boolean favorited;
    public ArrayList<String> pic_urls=new ArrayList<>();
    public ArrayList<String> middle_pic_urls=new ArrayList<>();
    public ArrayList<String> large_pic_urls=new ArrayList<>();

    /**
     * 此条微博用户信息
     */
    public UserEntity user;

    /**
     * 转发的微博内容
     */
    public WeiboContentEntity retweeted_status;
    public int reposts_count;
    public int comments_count;
    public int attitudes_count;

    public  static WeiboContentEntity parse(JSONObject jsonObject){
        if(jsonObject==null){
            return  null;
        }
        WeiboContentEntity weiboContentEntity =new WeiboContentEntity();
        weiboContentEntity.created_at=FormatDateUtil.formatDate(jsonObject.optString("created_at", ""));
        weiboContentEntity.id=jsonObject.optLong("id", 0);
        weiboContentEntity.text=jsonObject.optString("text", "");
        weiboContentEntity.source=jsonObject.optString("source", "");
        weiboContentEntity.favorited=jsonObject.optBoolean("favorited", false);
        JSONArray jsonArray=jsonObject.optJSONArray("pic_urls");
        if(jsonArray!=null){
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.optJSONObject(i);
                String thumbnail_pic_url=object.optString("thumbnail_pic", "");
                String middle_pic_url=thumbnail_pic_url.replace("thumbnail","bmiddle");
                String large_pic_url=thumbnail_pic_url.replace("thumbnail","large");
                weiboContentEntity.pic_urls.add(thumbnail_pic_url);
                weiboContentEntity.middle_pic_urls.add(middle_pic_url);
                weiboContentEntity.large_pic_urls.add(large_pic_url);
            }
        }
        weiboContentEntity.user= UserEntity.parse(jsonObject.optJSONObject("user"));
        weiboContentEntity.retweeted_status= WeiboContentEntity.parse(jsonObject.optJSONObject("retweeted_status"));
        weiboContentEntity.reposts_count=jsonObject.optInt("reposts_count", 0);
        weiboContentEntity.comments_count=jsonObject.optInt("comments_count", 0);
        weiboContentEntity.attitudes_count=jsonObject.optInt("attitudes_count",0);
        return weiboContentEntity;
    }
    public static WeiboContentEntity parse(String jsonString){
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
        dest.writeLong(this.id);
        dest.writeString(this.text);
        dest.writeString(this.source);
        dest.writeByte(this.favorited ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.pic_urls);
        dest.writeStringList(this.middle_pic_urls);
        dest.writeStringList(this.large_pic_urls);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.retweeted_status, flags);
        dest.writeInt(this.reposts_count);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.attitudes_count);
    }

    public WeiboContentEntity() {
    }

    protected WeiboContentEntity(Parcel in) {
        this.created_at = in.readString();
        this.id = in.readLong();
        this.text = in.readString();
        this.source = in.readString();
        this.favorited = in.readByte() != 0;
        this.pic_urls = in.createStringArrayList();
        this.middle_pic_urls = in.createStringArrayList();
        this.large_pic_urls = in.createStringArrayList();
        this.user = in.readParcelable(UserEntity.class.getClassLoader());
        this.retweeted_status = in.readParcelable(WeiboContentEntity.class.getClassLoader());
        this.reposts_count = in.readInt();
        this.comments_count = in.readInt();
        this.attitudes_count = in.readInt();
    }

    public static final Parcelable.Creator<WeiboContentEntity> CREATOR = new Parcelable.Creator<WeiboContentEntity>() {
        @Override
        public WeiboContentEntity createFromParcel(Parcel source) {
            return new WeiboContentEntity(source);
        }

        @Override
        public WeiboContentEntity[] newArray(int size) {
            return new WeiboContentEntity[size];
        }
    };
}
