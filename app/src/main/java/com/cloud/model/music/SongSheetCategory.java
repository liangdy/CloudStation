package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: SongSheetCategory.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/11/17 6:26 PM
 * Editor: ldy
 * Modify Date: 8/11/17 6:26 PM
 * Remark:
 */
public class SongSheetCategory implements Parcelable {

    public int error_code;
    public List<ContentBean> content;

    public SongSheetCategory() {
    }

    protected SongSheetCategory(Parcel in) {
        error_code = in.readInt();
        content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<SongSheetCategory> CREATOR = new Creator<SongSheetCategory>() {
        @Override
        public SongSheetCategory createFromParcel(Parcel in) {
            return new SongSheetCategory(in);
        }

        @Override
        public SongSheetCategory[] newArray(int size) {
            return new SongSheetCategory[size];
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

        public String title;
        public int num;
        public List<TagsBean> tags;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            title = in.readString();
            num = in.readInt();
            tags = in.createTypedArrayList(TagsBean.CREATOR);
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
            dest.writeInt(num);
            dest.writeTypedList(tags);
        }
    }

    public static class TagsBean implements Parcelable {

        public String type;
        public String tag;

        public TagsBean() {
        }

        protected TagsBean(Parcel in) {
            type = in.readString();
            tag = in.readString();
        }

        public static final Creator<TagsBean> CREATOR = new Creator<TagsBean>() {
            @Override
            public TagsBean createFromParcel(Parcel in) {
                return new TagsBean(in);
            }

            @Override
            public TagsBean[] newArray(int size) {
                return new TagsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(type);
            dest.writeString(tag);
        }
    }
}
