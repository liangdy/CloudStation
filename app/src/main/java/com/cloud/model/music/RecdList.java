package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: CloudStation
 * FileName: HomeRecd.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/3/17 2:33 PM
 * Editor: ldy
 * Modify Date: 8/3/17 2:33 PM
 * Remark:
 */
public class RecdList implements Parcelable {

    public static final int TYPE_HOT_SONG_SHEET = 0x01;
    public static final int TYPE_RECD_ALBUM = 0x02;
    public static final int TYPE_RECD_MUSIC = 0x03;

    public Object object;
    public int type;

    public RecdList() {
    }

    protected RecdList(Parcel in) {
        type = in.readInt();
    }

    public static final Creator<RecdList> CREATOR = new Creator<RecdList>() {
        @Override
        public RecdList createFromParcel(Parcel in) {
            return new RecdList(in);
        }

        @Override
        public RecdList[] newArray(int size) {
            return new RecdList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
    }
}
