package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: RankDetail.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 5:31 PM
 * Editor: ldy
 * Modify Date: 9/1/17 5:31 PM
 * Remark:
 */
public class RankDetail implements Parcelable {

    public int error_code;

    public BillboardBean billboard;

    public List<ContentBean> song_list;

    public RankDetail() {
    }

    protected RankDetail(Parcel in) {
        error_code = in.readInt();
        billboard = in.readParcelable(BillboardBean.class.getClassLoader());
        song_list = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<RankDetail> CREATOR = new Creator<RankDetail>() {
        @Override
        public RankDetail createFromParcel(Parcel in) {
            return new RankDetail(in);
        }

        @Override
        public RankDetail[] newArray(int size) {
            return new RankDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeParcelable(billboard, flags);
        dest.writeTypedList(song_list);
    }

    public static class BillboardBean implements Parcelable {

        public String billboard_type;
        public String billboard_no;
        public String update_date;
        public String billboard_songnum;
        public int havemore;
        public String name;
        public String comment;
        public String pic_s640;
        public String pic_s444;
        public String pic_s260;
        public String pic_s210;
        public String web_url;

        public BillboardBean() {
        }

        protected BillboardBean(Parcel in) {
            billboard_type = in.readString();
            billboard_no = in.readString();
            update_date = in.readString();
            billboard_songnum = in.readString();
            havemore = in.readInt();
            name = in.readString();
            comment = in.readString();
            pic_s640 = in.readString();
            pic_s444 = in.readString();
            pic_s260 = in.readString();
            pic_s210 = in.readString();
            web_url = in.readString();
        }

        public static final Creator<BillboardBean> CREATOR = new Creator<BillboardBean>() {
            @Override
            public BillboardBean createFromParcel(Parcel in) {
                return new BillboardBean(in);
            }

            @Override
            public BillboardBean[] newArray(int size) {
                return new BillboardBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(billboard_type);
            dest.writeString(billboard_no);
            dest.writeString(update_date);
            dest.writeString(billboard_songnum);
            dest.writeInt(havemore);
            dest.writeString(name);
            dest.writeString(comment);
            dest.writeString(pic_s640);
            dest.writeString(pic_s444);
            dest.writeString(pic_s260);
            dest.writeString(pic_s210);
            dest.writeString(web_url);
        }
    }
}
