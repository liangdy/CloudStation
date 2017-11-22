package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: AlbumDetail.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/23/17 6:40 PM
 * Editor: ldy
 * Modify Date: 8/23/17 6:40 PM
 * Remark:
 */
public class AlbumDetail implements Parcelable {

    public AlbumInfo albumInfo;
    public int is_collect;
    public List<SongListBean> songlist;

    public AlbumDetail() {
    }

    protected AlbumDetail(Parcel in) {
        albumInfo = in.readParcelable(AlbumInfo.class.getClassLoader());
        is_collect = in.readInt();
        songlist = in.createTypedArrayList(SongListBean.CREATOR);
    }

    public static final Creator<AlbumDetail> CREATOR = new Creator<AlbumDetail>() {
        @Override
        public AlbumDetail createFromParcel(Parcel in) {
            return new AlbumDetail(in);
        }

        @Override
        public AlbumDetail[] newArray(int size) {
            return new AlbumDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(albumInfo, flags);
        dest.writeInt(is_collect);
        dest.writeTypedList(songlist);
    }

    public static class SongListBean implements Parcelable {

        public String artist_id;
        public String all_artist_id;
        public String all_artist_ting_uid;
        public String language;
        public String publishtime;
        public String album_no;
        public String versions;
        public String pic_big;
        public String pic_small;
        public String hot;
        public String file_duration;
        public String del_status;
        public String resource_type;
        public String copy_type;
        public int has_mv_mobile;
        public String all_rate;
        public String toneid;
        public String country;
        public String area;
        public String lrclink;
        public String bitrate_fee;
        public String si_presale_flag;
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

        public SongListBean() {
        }

        protected SongListBean(Parcel in) {
            artist_id = in.readString();
            all_artist_id = in.readString();
            all_artist_ting_uid = in.readString();
            language = in.readString();
            publishtime = in.readString();
            album_no = in.readString();
            versions = in.readString();
            pic_big = in.readString();
            pic_small = in.readString();
            hot = in.readString();
            file_duration = in.readString();
            del_status = in.readString();
            resource_type = in.readString();
            copy_type = in.readString();
            has_mv_mobile = in.readInt();
            all_rate = in.readString();
            toneid = in.readString();
            country = in.readString();
            area = in.readString();
            lrclink = in.readString();
            bitrate_fee = in.readString();
            si_presale_flag = in.readString();
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
            dest.writeString(all_artist_id);
            dest.writeString(all_artist_ting_uid);
            dest.writeString(language);
            dest.writeString(publishtime);
            dest.writeString(album_no);
            dest.writeString(versions);
            dest.writeString(pic_big);
            dest.writeString(pic_small);
            dest.writeString(hot);
            dest.writeString(file_duration);
            dest.writeString(del_status);
            dest.writeString(resource_type);
            dest.writeString(copy_type);
            dest.writeInt(has_mv_mobile);
            dest.writeString(all_rate);
            dest.writeString(toneid);
            dest.writeString(country);
            dest.writeString(area);
            dest.writeString(lrclink);
            dest.writeString(bitrate_fee);
            dest.writeString(si_presale_flag);
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
        }
    }
}
