package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: NewMusic.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/3/17 9:54 AM
 * Editor: ldy
 * Modify Date: 8/3/17 9:54 AM
 * Remark:
 */
public class Music {

    public int error_code;
    public List<ContentBean> content;

    public static class ContentBean implements Parcelable {

        public String title;
        public List<SongListBean> song_list;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            title = in.readString();
            song_list = in.createTypedArrayList(SongListBean.CREATOR);
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
            dest.writeTypedList(song_list);
        }
    }

    public static class SongListBean implements Parcelable {

        public String artist_id;
        public String pic_big;
        public String pic_small;
        public String pic_premium;
        public String pic_huge;
        public String pic_singer;
        public String all_artist_ting_uid;
        public String file_duration;
        public String del_status;
        public String resource_type;
        public String all_rate;
        public String toneid;
        public String copy_type;
        public int has_mv_mobile;
        public String song_id;
        public String title;
        public String ting_uid;
        public String author;
        public String album_id;
        public String album_title;
        public int is_first_publish;
        public int havehigh;
        public int charge;
        public int has_mv;
        public int learn;
        public String song_source;
        public String piao_id;
        public String korean_bb_song;
        public String resource_type_ext;
        public String mv_provider;
        public String desc;
        public String url;
        public String recommend_reason;

        public SongListBean() {
        }

        protected SongListBean(Parcel in) {
            artist_id = in.readString();
            pic_big = in.readString();
            pic_small = in.readString();
            pic_premium = in.readString();
            pic_huge = in.readString();
            pic_singer = in.readString();
            all_artist_ting_uid = in.readString();
            file_duration = in.readString();
            del_status = in.readString();
            resource_type = in.readString();
            all_rate = in.readString();
            toneid = in.readString();
            copy_type = in.readString();
            has_mv_mobile = in.readInt();
            song_id = in.readString();
            title = in.readString();
            ting_uid = in.readString();
            author = in.readString();
            album_id = in.readString();
            album_title = in.readString();
            is_first_publish = in.readInt();
            havehigh = in.readInt();
            charge = in.readInt();
            has_mv = in.readInt();
            learn = in.readInt();
            song_source = in.readString();
            piao_id = in.readString();
            korean_bb_song = in.readString();
            resource_type_ext = in.readString();
            mv_provider = in.readString();
            desc = in.readString();
            url = in.readString();
            recommend_reason = in.readString();
        }

        public static final Creator<SongListBean> CREATOR = new Creator<SongListBean>() {
            @Override
            public SongListBean createFromParcel(Parcel in) {
                return new SongListBean(in);
            }

            @Override
            public SongListBean[] newArray(int size) {
                return new SongListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(artist_id);
            dest.writeString(pic_big);
            dest.writeString(pic_small);
            dest.writeString(pic_premium);
            dest.writeString(pic_huge);
            dest.writeString(pic_singer);
            dest.writeString(all_artist_ting_uid);
            dest.writeString(file_duration);
            dest.writeString(del_status);
            dest.writeString(resource_type);
            dest.writeString(all_rate);
            dest.writeString(toneid);
            dest.writeString(copy_type);
            dest.writeInt(has_mv_mobile);
            dest.writeString(song_id);
            dest.writeString(title);
            dest.writeString(ting_uid);
            dest.writeString(author);
            dest.writeString(album_id);
            dest.writeString(album_title);
            dest.writeInt(is_first_publish);
            dest.writeInt(havehigh);
            dest.writeInt(charge);
            dest.writeInt(has_mv);
            dest.writeInt(learn);
            dest.writeString(song_source);
            dest.writeString(piao_id);
            dest.writeString(korean_bb_song);
            dest.writeString(resource_type_ext);
            dest.writeString(mv_provider);
            dest.writeString(desc);
            dest.writeString(url);
            dest.writeString(recommend_reason);
        }
    }

}
