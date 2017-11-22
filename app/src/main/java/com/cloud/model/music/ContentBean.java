package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: CloudStation
 * FileName: ContentBean.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 3:19 PM
 * Editor: ldy
 * Modify Date: 8/22/17 3:19 PM
 * Remark:
 */
public class ContentBean implements Parcelable {

    public String title;
    public final static String KEY_TITLE = "title";
    public String song_id;
    public final static String KEY_SONG_ID = "song_id";
    public String author;
    public final static String KEY_AUTHOR = "author";
    public String album_id;
    public final static String KEY_ALBUM_ID = "album_id";
    public String album_title;
    public final static String KEY_ALBUM_TITLE = "album_title";
    public String relate_status;
    public final static String KEY_RELATE_STATUS = "relate_status";
    public String is_charge;
    public final static String KEY_IS_CHARGE = "is_charge";
    public String all_rate;
    public final static String KEY_ALL_RATE = "all_rate";
    public String high_rate;
    public final static String KEY_HIGH_RATE = "high_rate";
    public String all_artist_id;
    public final static String KEY_ALL_ARTIST_ID = "all_artist_id";
    public String copy_type;
    public final static String KEY_COPY_TYPE = "copy_type";
    public int has_mv;
    public final static String KEY_HAS_MV = "has_mv";
    public String toneid;
    public final static String KEY_TONEID = "toneid";
    public String resource_type;
    public final static String KEY_RESOURCE_TYPE = "resource_type";
    public String is_ksong;
    public final static String KEY_IS_KSONG = "is_ksong";
    public String resource_type_ext;
    public final static String KEY_RESOURCE_TYPE_EXT = "resource_type_ext";
    public String versions;
    public final static String KEY_VERSIONS = "versions";
    public String bitrate_fee;
    public final static String KEY_BITRATE_FEE = "bitrate_fee";
    public int has_mv_mobile;
    public final static String KEY_HAS_MV_MOBILE = "has_mv_mobile";
    public String ting_uid;
    public final static String KEY_TING_UID = "ting_uid";
    public int is_first_publish;
    public final static String KEY_IS_FIRST_PUBLISH = "is_first_publish";
    public int havehigh;
    public final static String KEY_HAVEHIGH = "havehigh";
    public int charge;
    public final static String KEY_CHARGE = "charge";
    public int learn;
    public final static String KEY_LEARN = "learn";
    public String song_source;
    public final static String KEY_SONG_SOURCE = "song_source";
    public String piao_id;
    public final static String KEY_PIAO_ID = "piao_id";
    public String korean_bb_song;
    public final static String KEY_KOREAN_BB_SONG = "korean_bb_song";
    public String mv_provider;
    public final static String KEY_MV_PROVIDER = "mv_provider";
    public String share;
    public final static String KEY_SHARE = "share";
    public String localUrl;
    public final static String KEY_LOCALURL = "localUrl";
    public int duration;
    public final static String KEY_DURATION = "duration";
    public long size;
    public final static String KEY_SIZE = "size";
    public String pic_url;
    public final static String KEY_PIC_URL = "pic_url";
    public String lrc_url;
    public final static String KEY_LRC_URL = "lrc_url";

    public String pinyin;

    public ContentBean() {
    }

    protected ContentBean(Parcel in) {
        title = in.readString();
        song_id = in.readString();
        author = in.readString();
        album_id = in.readString();
        album_title = in.readString();
        relate_status = in.readString();
        is_charge = in.readString();
        all_rate = in.readString();
        high_rate = in.readString();
        all_artist_id = in.readString();
        copy_type = in.readString();
        has_mv = in.readInt();
        toneid = in.readString();
        resource_type = in.readString();
        is_ksong = in.readString();
        resource_type_ext = in.readString();
        versions = in.readString();
        bitrate_fee = in.readString();
        has_mv_mobile = in.readInt();
        ting_uid = in.readString();
        is_first_publish = in.readInt();
        havehigh = in.readInt();
        charge = in.readInt();
        learn = in.readInt();
        song_source = in.readString();
        piao_id = in.readString();
        korean_bb_song = in.readString();
        mv_provider = in.readString();
        share = in.readString();
        localUrl = in.readString();
        duration = in.readInt();
        size = in.readLong();
        pic_url = in.readString();
        lrc_url = in.readString();
        pinyin = in.readString();
    }

    public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
        @Override
        public ContentBean createFromParcel(Parcel in) {
            return new ContentBean(in);
        }

        @Override
        public ContentBean[] newArray(int size) {
            return new ContentBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(song_id);
        dest.writeString(author);
        dest.writeString(album_id);
        dest.writeString(album_title);
        dest.writeString(relate_status);
        dest.writeString(is_charge);
        dest.writeString(all_rate);
        dest.writeString(high_rate);
        dest.writeString(all_artist_id);
        dest.writeString(copy_type);
        dest.writeInt(has_mv);
        dest.writeString(toneid);
        dest.writeString(resource_type);
        dest.writeString(is_ksong);
        dest.writeString(resource_type_ext);
        dest.writeString(versions);
        dest.writeString(bitrate_fee);
        dest.writeInt(has_mv_mobile);
        dest.writeString(ting_uid);
        dest.writeInt(is_first_publish);
        dest.writeInt(havehigh);
        dest.writeInt(charge);
        dest.writeInt(learn);
        dest.writeString(song_source);
        dest.writeString(piao_id);
        dest.writeString(korean_bb_song);
        dest.writeString(mv_provider);
        dest.writeString(share);
        dest.writeString(localUrl);
        dest.writeInt(duration);
        dest.writeLong(size);
        dest.writeString(pic_url);
        dest.writeString(lrc_url);
        dest.writeString(pinyin);
    }
}
