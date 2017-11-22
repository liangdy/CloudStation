package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: SongSheetDetail.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 3:15 PM
 * Editor: ldy
 * Modify Date: 8/22/17 3:15 PM
 * Remark:
 */
public class SongSheetDetail implements Parcelable {

    public int error_code;
    public String listid;
    public String title;
    public String pic_300;
    public String pic_500;
    public String pic_w700;
    public String width;
    public String height;
    public String listenum;
    public String collectnum;
    public String tag;
    public String desc;
    public String url;
    public List<ContentBean> content;

    public SongSheetDetail() {
    }

    protected SongSheetDetail(Parcel in) {
        error_code = in.readInt();
        listid = in.readString();
        title = in.readString();
        pic_300 = in.readString();
        pic_500 = in.readString();
        pic_w700 = in.readString();
        width = in.readString();
        height = in.readString();
        listenum = in.readString();
        collectnum = in.readString();
        tag = in.readString();
        desc = in.readString();
        url = in.readString();
        content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<SongSheetDetail> CREATOR = new Creator<SongSheetDetail>() {
        @Override
        public SongSheetDetail createFromParcel(Parcel in) {
            return new SongSheetDetail(in);
        }

        @Override
        public SongSheetDetail[] newArray(int size) {
            return new SongSheetDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeString(listid);
        dest.writeString(title);
        dest.writeString(pic_300);
        dest.writeString(pic_500);
        dest.writeString(pic_w700);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeString(listenum);
        dest.writeString(collectnum);
        dest.writeString(tag);
        dest.writeString(desc);
        dest.writeString(url);
        dest.writeTypedList(content);
    }
}
