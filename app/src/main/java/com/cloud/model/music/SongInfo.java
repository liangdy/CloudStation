package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: CloudStation
 * FileName: SongInfo.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 11/25/17 9:43 PM
 * Editor: ldy
 * Modify Date: 11/25/17 9:43 PM
 * Remark:
 */
public class SongInfo implements Parcelable {

    public int error_code;
    public SongInfoBean songinfo;
    public BitrateBean bitrate;

    public SongInfo() {
    }

    protected SongInfo(Parcel in) {
        error_code = in.readInt();
        songinfo = in.readParcelable(SongInfoBean.class.getClassLoader());
        bitrate = in.readParcelable(BitrateBean.class.getClassLoader());
    }

    public static final Creator<SongInfo> CREATOR = new Creator<SongInfo>() {
        @Override
        public SongInfo createFromParcel(Parcel in) {
            return new SongInfo(in);
        }

        @Override
        public SongInfo[] newArray(int size) {
            return new SongInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeParcelable(songinfo, flags);
        dest.writeParcelable(bitrate, flags);
    }

    public static class SongInfoBean implements Parcelable {

        public int special_type;
        public String pic_huge;
        public String resource_type;
        public String pic_premium;
        public int havehigh;
        public String author;
        public String toneid;
        public int has_mv;
        public String song_id;
        public String piao_id;
        public String artist_id;
        public String lrclink;
        public String relate_status;
        public int learn;
        public String pic_big;
        public int play_type;
        public String album_id;
        public String album_title;
        public String bitrate_fee;
        public String song_source;
        public String all_artist_id;
        public String all_artist_ting_uid;
        public String all_rate;
        public int charge;
        public String copy_type;
        public int is_first_publish;
        public String korean_bb_song;
        public String pic_radio;
        public int has_mv_mobile;
        public String title;
        public String pic_small;
        public String album_no;
        public String resource_type_ext;
        private String ting_uid;

        public SongInfoBean() {
        }

        protected SongInfoBean(Parcel in) {
            special_type = in.readInt();
            pic_huge = in.readString();
            resource_type = in.readString();
            pic_premium = in.readString();
            havehigh = in.readInt();
            author = in.readString();
            toneid = in.readString();
            has_mv = in.readInt();
            song_id = in.readString();
            piao_id = in.readString();
            artist_id = in.readString();
            lrclink = in.readString();
            relate_status = in.readString();
            learn = in.readInt();
            pic_big = in.readString();
            play_type = in.readInt();
            album_id = in.readString();
            album_title = in.readString();
            bitrate_fee = in.readString();
            song_source = in.readString();
            all_artist_id = in.readString();
            all_artist_ting_uid = in.readString();
            all_rate = in.readString();
            charge = in.readInt();
            copy_type = in.readString();
            is_first_publish = in.readInt();
            korean_bb_song = in.readString();
            pic_radio = in.readString();
            has_mv_mobile = in.readInt();
            title = in.readString();
            pic_small = in.readString();
            album_no = in.readString();
            resource_type_ext = in.readString();
            ting_uid = in.readString();
        }

        public static final Creator<SongInfoBean> CREATOR = new Creator<SongInfoBean>() {
            @Override
            public SongInfoBean createFromParcel(Parcel in) {
                return new SongInfoBean(in);
            }

            @Override
            public SongInfoBean[] newArray(int size) {
                return new SongInfoBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(special_type);
            dest.writeString(pic_huge);
            dest.writeString(resource_type);
            dest.writeString(pic_premium);
            dest.writeInt(havehigh);
            dest.writeString(author);
            dest.writeString(toneid);
            dest.writeInt(has_mv);
            dest.writeString(song_id);
            dest.writeString(piao_id);
            dest.writeString(artist_id);
            dest.writeString(lrclink);
            dest.writeString(relate_status);
            dest.writeInt(learn);
            dest.writeString(pic_big);
            dest.writeInt(play_type);
            dest.writeString(album_id);
            dest.writeString(album_title);
            dest.writeString(bitrate_fee);
            dest.writeString(song_source);
            dest.writeString(all_artist_id);
            dest.writeString(all_artist_ting_uid);
            dest.writeString(all_rate);
            dest.writeInt(charge);
            dest.writeString(copy_type);
            dest.writeInt(is_first_publish);
            dest.writeString(korean_bb_song);
            dest.writeString(pic_radio);
            dest.writeInt(has_mv_mobile);
            dest.writeString(title);
            dest.writeString(pic_small);
            dest.writeString(album_no);
            dest.writeString(resource_type_ext);
            dest.writeString(ting_uid);
        }
    }

    public static class BitrateBean implements Parcelable {
        public String show_link;
        public int free;
        public int song_file_id;
        public int file_size;
        public String file_extension;
        public int file_duration;
        public int file_bitrate;
        public String file_link;
        public String hash;

        public BitrateBean() {
        }

        protected BitrateBean(Parcel in) {
            show_link = in.readString();
            free = in.readInt();
            song_file_id = in.readInt();
            file_size = in.readInt();
            file_extension = in.readString();
            file_duration = in.readInt();
            file_bitrate = in.readInt();
            file_link = in.readString();
            hash = in.readString();
        }

        public static final Creator<BitrateBean> CREATOR = new Creator<BitrateBean>() {
            @Override
            public BitrateBean createFromParcel(Parcel in) {
                return new BitrateBean(in);
            }

            @Override
            public BitrateBean[] newArray(int size) {
                return new BitrateBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(show_link);
            dest.writeInt(free);
            dest.writeInt(song_file_id);
            dest.writeInt(file_size);
            dest.writeString(file_extension);
            dest.writeInt(file_duration);
            dest.writeInt(file_bitrate);
            dest.writeString(file_link);
            dest.writeString(hash);
        }
    }
}
