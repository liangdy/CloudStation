package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: SongSheet.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/2/17 5:42 PM
 * Editor: ldy
 * Modify Date: 8/2/17 5:42 PM
 * Remark:
 */
public class SongSheet implements Parcelable {

    public int error_code;
    public ContentBean content;

    public SongSheet() {
    }

    protected SongSheet(Parcel in) {
        error_code = in.readInt();
        content = in.readParcelable(ContentBean.class.getClassLoader());
    }

    public static final Creator<SongSheet> CREATOR = new Creator<SongSheet>() {
        @Override
        public SongSheet createFromParcel(Parcel in) {
            return new SongSheet(in);
        }

        @Override
        public SongSheet[] newArray(int size) {
            return new SongSheet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeParcelable(content, flags);
    }

    public static class ContentBean implements Parcelable {

        public String title;
        public List<SongSheetBean> list;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            title = in.readString();
            list = in.createTypedArrayList(SongSheetBean.CREATOR);
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
            dest.writeTypedList(list);
        }
    }

    public static class SongSheetBean implements Parcelable {

        public String listid;
        public String pic;
        public String listenum;
        public String collectnum;
        public String title;
        public String tag;
        public String type;

        public SongSheetBean() {
        }

        protected SongSheetBean(Parcel in) {
            listid = in.readString();
            pic = in.readString();
            listenum = in.readString();
            collectnum = in.readString();
            title = in.readString();
            tag = in.readString();
            type = in.readString();
        }

        public static final Creator<SongSheetBean> CREATOR = new Creator<SongSheetBean>() {
            @Override
            public SongSheetBean createFromParcel(Parcel in) {
                return new SongSheetBean(in);
            }

            @Override
            public SongSheetBean[] newArray(int size) {
                return new SongSheetBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(listid);
            dest.writeString(pic);
            dest.writeString(listenum);
            dest.writeString(collectnum);
            dest.writeString(title);
            dest.writeString(tag);
            dest.writeString(type);
        }
    }
}
