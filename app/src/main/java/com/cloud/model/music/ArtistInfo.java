package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: CloudStation
 * FileName: ArtistInfo.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/15/17 3:01 PM
 * Editor: ldy
 * Modify Date: 8/15/17 3:01 PM
 * Remark:
 */
public class ArtistInfo implements Parcelable {

    public String artist_name;
    public int number_of_tracks;
    public long artist_id;
    public String artist_sort;

    public ArtistInfo() {
    }

    protected ArtistInfo(Parcel in) {
        artist_name = in.readString();
        number_of_tracks = in.readInt();
        artist_id = in.readLong();
        artist_sort = in.readString();
    }

    public static final Creator<ArtistInfo> CREATOR = new Creator<ArtistInfo>() {
        @Override
        public ArtistInfo createFromParcel(Parcel in) {
            return new ArtistInfo(in);
        }

        @Override
        public ArtistInfo[] newArray(int size) {
            return new ArtistInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artist_name);
        dest.writeInt(number_of_tracks);
        dest.writeLong(artist_id);
        dest.writeString(artist_sort);
    }
}
