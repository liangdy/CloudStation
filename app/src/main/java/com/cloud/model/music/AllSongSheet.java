package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: AllSongSheet.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/9/17 6:30 PM
 * Editor: ldy
 * Modify Date: 8/9/17 6:30 PM
 * Remark:
 */
public class AllSongSheet implements Parcelable {

    public int havemore;
    public int error_code;
    public int total;
    public List<ContentBean> content;

    public AllSongSheet() {
    }

    protected AllSongSheet(Parcel in) {
        havemore = in.readInt();
        error_code = in.readInt();
        total = in.readInt();
        content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<AllSongSheet> CREATOR = new Creator<AllSongSheet>() {
        @Override
        public AllSongSheet createFromParcel(Parcel in) {
            return new AllSongSheet(in);
        }

        @Override
        public AllSongSheet[] newArray(int size) {
            return new AllSongSheet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(havemore);
        dest.writeInt(error_code);
        dest.writeInt(total);
        dest.writeTypedList(content);
    }

    public static class ContentBean implements Parcelable {

        public String width;
        public String tag;
        public String pic_300;
        public String desc;
        public String pic_w300;
        public String title;
        public String collectnum;
        public String listenum;
        public String height;
        public String listid;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            width = in.readString();
            tag = in.readString();
            pic_300 = in.readString();
            desc = in.readString();
            pic_w300 = in.readString();
            title = in.readString();
            collectnum = in.readString();
            listenum = in.readString();
            height = in.readString();
            listid = in.readString();
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
            dest.writeString(width);
            dest.writeString(tag);
            dest.writeString(pic_300);
            dest.writeString(desc);
            dest.writeString(pic_w300);
            dest.writeString(title);
            dest.writeString(collectnum);
            dest.writeString(listenum);
            dest.writeString(height);
            dest.writeString(listid);
        }
    }
}
