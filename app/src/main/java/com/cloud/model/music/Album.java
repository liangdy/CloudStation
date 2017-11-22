package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: Album.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/10/17 3:03 PM
 * Editor: ldy
 * Modify Date: 3/10/17 3:03 PM
 * Remark:
 */
public class Album implements Parcelable {

    public int error_code;
    public PlazeAlbumBean plaze_album_list;

    public Album() {
    }

    protected Album(Parcel in) {
        error_code = in.readInt();
        plaze_album_list = in.readParcelable(PlazeAlbumBean.class.getClassLoader());
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeParcelable(plaze_album_list, flags);
    }

    public static class PlazeAlbumBean implements Parcelable {

        public RMBean RM;

        public PlazeAlbumBean() {
        }

        protected PlazeAlbumBean(Parcel in) {
            RM = in.readParcelable(RMBean.class.getClassLoader());
        }

        public static final Creator<PlazeAlbumBean> CREATOR = new Creator<PlazeAlbumBean>() {
            @Override
            public PlazeAlbumBean createFromParcel(Parcel in) {
                return new PlazeAlbumBean(in);
            }

            @Override
            public PlazeAlbumBean[] newArray(int size) {
                return new PlazeAlbumBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(RM, flags);
        }
    }

    public static class RMBean implements Parcelable {

        public AlbumListBean album_list;

        public RMBean() {
        }

        protected RMBean(Parcel in) {
            album_list = in.readParcelable(AlbumListBean.class.getClassLoader());
        }

        public static final Creator<RMBean> CREATOR = new Creator<RMBean>() {
            @Override
            public RMBean createFromParcel(Parcel in) {
                return new RMBean(in);
            }

            @Override
            public RMBean[] newArray(int size) {
                return new RMBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(album_list, flags);
        }
    }

    public static class AlbumListBean implements Parcelable {

        public int havemore;
        public List<ListBean> list;

        public AlbumListBean() {
        }

        protected AlbumListBean(Parcel in) {
            havemore = in.readInt();
            list = in.createTypedArrayList(ListBean.CREATOR);
        }

        public static final Creator<AlbumListBean> CREATOR = new Creator<AlbumListBean>() {
            @Override
            public AlbumListBean createFromParcel(Parcel in) {
                return new AlbumListBean(in);
            }

            @Override
            public AlbumListBean[] newArray(int size) {
                return new AlbumListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(havemore);
            dest.writeTypedList(list);
        }
    }

    public static class ListBean implements Parcelable {

        public String album_id;
        public String artist_id;
        public String all_artist_id;
        public String author;
        public String title;
        public String publishcompany;
        public String country;
        public String pic_small;
        public String pic_big;
        public String pic_radio;
        public String songs_total;
        public String is_recommend_mis;
        public String is_first_publish;
        public String is_exclusive;

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            album_id = in.readString();
            artist_id = in.readString();
            all_artist_id = in.readString();
            author = in.readString();
            title = in.readString();
            publishcompany = in.readString();
            country = in.readString();
            pic_small = in.readString();
            pic_big = in.readString();
            pic_radio = in.readString();
            songs_total = in.readString();
            is_recommend_mis = in.readString();
            is_first_publish = in.readString();
            is_exclusive = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(album_id);
            dest.writeString(artist_id);
            dest.writeString(all_artist_id);
            dest.writeString(author);
            dest.writeString(title);
            dest.writeString(publishcompany);
            dest.writeString(country);
            dest.writeString(pic_small);
            dest.writeString(pic_big);
            dest.writeString(pic_radio);
            dest.writeString(songs_total);
            dest.writeString(is_recommend_mis);
            dest.writeString(is_first_publish);
            dest.writeString(is_exclusive);
        }
    }
}
