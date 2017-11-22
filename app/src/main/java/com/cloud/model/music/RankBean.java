package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: RankBean.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/13/17 5:31 PM
 * Editor: ldy
 * Modify Date: 8/13/17 5:31 PM
 * Remark:
 */
public class RankBean implements Parcelable {

    public int error_code;
    public List<ContentBean> content;

    public RankBean() {
    }

    protected RankBean(Parcel in) {
        error_code = in.readInt();
        content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<RankBean> CREATOR = new Creator<RankBean>() {
        @Override
        public RankBean createFromParcel(Parcel in) {
            return new RankBean(in);
        }

        @Override
        public RankBean[] newArray(int size) {
            return new RankBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeTypedList(content);
    }

    public static class ContentBean implements Parcelable {

        public String name;
        public int type;
        public int count;
        public String comment;
        public String web_url;
        public String pic_s192;
        public String pic_s444;
        public String pic_s260;
        public String pic_s210;
        public List<RankContent> content;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            name = in.readString();
            type = in.readInt();
            count = in.readInt();
            comment = in.readString();
            web_url = in.readString();
            pic_s192 = in.readString();
            pic_s444 = in.readString();
            pic_s260 = in.readString();
            pic_s210 = in.readString();
            content = in.createTypedArrayList(RankContent.CREATOR);
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
            dest.writeString(name);
            dest.writeInt(type);
            dest.writeInt(count);
            dest.writeString(comment);
            dest.writeString(web_url);
            dest.writeString(pic_s192);
            dest.writeString(pic_s444);
            dest.writeString(pic_s260);
            dest.writeString(pic_s210);
            dest.writeTypedList(content);
        }
    }

    public static class RankContent implements Parcelable {

        public String title;
        public String author;
        public String song_id;
        public String album_id;
        public String album_title;
        public String rank_change;
        public String all_rate;

        public RankContent() {
        }

        protected RankContent(Parcel in) {
            title = in.readString();
            author = in.readString();
            song_id = in.readString();
            album_id = in.readString();
            album_title = in.readString();
            rank_change = in.readString();
            all_rate = in.readString();
        }

        public static final Creator<RankContent> CREATOR = new Creator<RankContent>() {
            @Override
            public RankContent createFromParcel(Parcel in) {
                return new RankContent(in);
            }

            @Override
            public RankContent[] newArray(int size) {
                return new RankContent[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(author);
            dest.writeString(song_id);
            dest.writeString(album_id);
            dest.writeString(album_title);
            dest.writeString(rank_change);
            dest.writeString(all_rate);
        }
    }
}
