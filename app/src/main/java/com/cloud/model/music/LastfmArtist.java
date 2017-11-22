package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: LastfmArtist.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 1:02 PM
 * Editor: ldy
 * Modify Date: 9/5/17 1:02 PM
 * Remark:
 */
public class LastfmArtist implements Parcelable {

    public Artist artist;

    public LastfmArtist() {
    }

    protected LastfmArtist(Parcel in) {
        artist = in.readParcelable(Artist.class.getClassLoader());
    }

    public static final Creator<LastfmArtist> CREATOR = new Creator<LastfmArtist>() {
        @Override
        public LastfmArtist createFromParcel(Parcel in) {
            return new LastfmArtist(in);
        }

        @Override
        public LastfmArtist[] newArray(int size) {
            return new LastfmArtist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(artist, flags);
    }

    public static class Artist implements Parcelable {

        public String name;

        public List<Artwork> image;

        public Artist() {
        }

        protected Artist(Parcel in) {
            name = in.readString();
            image = in.createTypedArrayList(Artwork.CREATOR);
        }

        public static final Creator<Artist> CREATOR = new Creator<Artist>() {
            @Override
            public Artist createFromParcel(Parcel in) {
                return new Artist(in);
            }

            @Override
            public Artist[] newArray(int size) {
                return new Artist[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeTypedList(image);
        }
    }

    public static class Artwork implements Parcelable {

        @JSONField(name = "#text")
        public String url;

        public String size;

        public Artwork() {
        }

        protected Artwork(Parcel in) {
            url = in.readString();
            size = in.readString();
        }

        public static final Creator<Artwork> CREATOR = new Creator<Artwork>() {
            @Override
            public Artwork createFromParcel(Parcel in) {
                return new Artwork(in);
            }

            @Override
            public Artwork[] newArray(int size) {
                return new Artwork[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
            dest.writeString(size);
        }
    }
}
