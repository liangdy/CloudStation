package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: CloudStation
 * FileName: AlbumInfo.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/15/17 3:02 PM
 * Editor: ldy
 * Modify Date: 8/15/17 3:02 PM
 * Remark:
 */
public class AlbumInfo implements Parcelable {

//    public static final String KEY_ALBUM_NAME = "album_name";
//    public static final String KEY_ALBUM_ID = "album_id";
//    public static final String KEY_NUMBER_OF_SONGS = "number_of_songs";
//    public static final String KEY_ALBUM_ART = "album_art";
//    public static final String KEY_ALBUM_ARTIST = "album_artist";
//    public static final String KEY_ALBUM_SORT = "album_sort";

    //专辑id
    public int album_id = -1;

    //local album
    //专辑名称
    public String album_name;
    //专辑的歌曲数目
    public int number_of_songs = 0;
    //专辑封面图片路径
    public String album_art;
    public String album_artist;
    public String album_sort;

    //online album
    public String author;
    public String title;
    public String publishcompany;
    public Object prodcompany;
    public String country;
    public String language;
    public String songs_total;
    public String info;
    public String styles;
    public Object style_id;
    public String publishtime;
    public String artist_ting_uid;
    public Object all_artist_ting_uid;
    public String gender;
    public String area;
    public String pic_small;
    public String pic_big;
    public String hot;
    public int favorites_num;
    public int recommend_num;
    public int collect_num;
    public int share_num;
    public int comment_num;
    public String artist_id;
    public String all_artist_id;
    public String pic_radio;
    public String pic_s500;
    public String pic_s1000;
    public String ai_presale_flag;
    public String resource_type_ext;
    public String listen_num;
    public String buy_url;

    public AlbumInfo() {
    }

    protected AlbumInfo(Parcel in) {
        album_id = in.readInt();
        album_name = in.readString();
        number_of_songs = in.readInt();
        album_art = in.readString();
        album_artist = in.readString();
        album_sort = in.readString();
        author = in.readString();
        title = in.readString();
        publishcompany = in.readString();
        country = in.readString();
        language = in.readString();
        songs_total = in.readString();
        info = in.readString();
        styles = in.readString();
        publishtime = in.readString();
        artist_ting_uid = in.readString();
        gender = in.readString();
        area = in.readString();
        pic_small = in.readString();
        pic_big = in.readString();
        hot = in.readString();
        favorites_num = in.readInt();
        recommend_num = in.readInt();
        collect_num = in.readInt();
        share_num = in.readInt();
        comment_num = in.readInt();
        artist_id = in.readString();
        all_artist_id = in.readString();
        pic_radio = in.readString();
        pic_s500 = in.readString();
        pic_s1000 = in.readString();
        ai_presale_flag = in.readString();
        resource_type_ext = in.readString();
        listen_num = in.readString();
        buy_url = in.readString();
    }

    public static final Creator<AlbumInfo> CREATOR = new Creator<AlbumInfo>() {
        @Override
        public AlbumInfo createFromParcel(Parcel in) {
            return new AlbumInfo(in);
        }

        @Override
        public AlbumInfo[] newArray(int size) {
            return new AlbumInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(album_id);
        dest.writeString(album_name);
        dest.writeInt(number_of_songs);
        dest.writeString(album_art);
        dest.writeString(album_artist);
        dest.writeString(album_sort);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(publishcompany);
        dest.writeString(country);
        dest.writeString(language);
        dest.writeString(songs_total);
        dest.writeString(info);
        dest.writeString(styles);
        dest.writeString(publishtime);
        dest.writeString(artist_ting_uid);
        dest.writeString(gender);
        dest.writeString(area);
        dest.writeString(pic_small);
        dest.writeString(pic_big);
        dest.writeString(hot);
        dest.writeInt(favorites_num);
        dest.writeInt(recommend_num);
        dest.writeInt(collect_num);
        dest.writeInt(share_num);
        dest.writeInt(comment_num);
        dest.writeString(artist_id);
        dest.writeString(all_artist_id);
        dest.writeString(pic_radio);
        dest.writeString(pic_s500);
        dest.writeString(pic_s1000);
        dest.writeString(ai_presale_flag);
        dest.writeString(resource_type_ext);
        dest.writeString(listen_num);
        dest.writeString(buy_url);
    }
    
    /*public static final Creator<AlbumInfo> CREATOR = new Creator<AlbumInfo>() {

        //读数据恢复数据
        @Override
        public AlbumInfo createFromParcel(Parcel source) {
            AlbumInfo info = new AlbumInfo();
            Bundle bundle = source.readBundle();
            info.album_name = bundle.getString(KEY_ALBUM_NAME);
            info.album_art = bundle.getString(KEY_ALBUM_ART);
            info.number_of_songs = bundle.getInt(KEY_NUMBER_OF_SONGS);
            info.album_id = bundle.getInt(KEY_ALBUM_ID);
            info.album_artist = bundle.getString(KEY_ALBUM_ARTIST);
            info.album_sort = bundle.getString(KEY_ALBUM_SORT);
            return info;
        }

        @Override
        public AlbumInfo[] newArray(int size) {
            return new AlbumInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //写数据保存数据
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ALBUM_NAME, album_name);
        bundle.putString(KEY_ALBUM_ART, album_art);
        bundle.putInt(KEY_NUMBER_OF_SONGS, number_of_songs);
        bundle.putInt(KEY_ALBUM_ID, album_id);
        bundle.putString(KEY_ALBUM_ARTIST, album_artist);
        bundle.putString(KEY_ALBUM_SORT, album_sort);
        dest.writeBundle(bundle);
    }*/
}
