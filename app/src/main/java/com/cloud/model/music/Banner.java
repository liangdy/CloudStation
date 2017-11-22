package com.cloud.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: Banner.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/15/17 11:20 AM
 * Editor: ldy
 * Modify Date: 3/15/17 11:20 AM
 * Remark:
 */
public class Banner implements Parcelable {
    public int error_code;
    public List<PicBean> pic;

    public Banner() {
    }

    protected Banner(Parcel in) {
        error_code = in.readInt();
        pic = in.createTypedArrayList(PicBean.CREATOR);
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel in) {
            return new Banner(in);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error_code);
        dest.writeTypedList(pic);
    }

    public static class PicBean implements Parcelable {

        public PicBean() {
        }

        public String randpic;
        public String randpic_ios5;
        public String randpic_desc;
        public String randpic_ipad;
        public String randpic_qq;
        public String randpic_2;
        public String randpic_iphone6;
        public int special_type;
        public String ipad_desc;
        public String is_publish;
        public String mo_type;
        public int type;
        public String code;

        protected PicBean(Parcel in) {
            randpic = in.readString();
            randpic_ios5 = in.readString();
            randpic_desc = in.readString();
            randpic_ipad = in.readString();
            randpic_qq = in.readString();
            randpic_2 = in.readString();
            randpic_iphone6 = in.readString();
            special_type = in.readInt();
            ipad_desc = in.readString();
            is_publish = in.readString();
            mo_type = in.readString();
            type = in.readInt();
            code = in.readString();
        }

        public static final Creator<PicBean> CREATOR = new Creator<PicBean>() {
            @Override
            public PicBean createFromParcel(Parcel in) {
                return new PicBean(in);
            }

            @Override
            public PicBean[] newArray(int size) {
                return new PicBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(randpic);
            dest.writeString(randpic_ios5);
            dest.writeString(randpic_desc);
            dest.writeString(randpic_ipad);
            dest.writeString(randpic_qq);
            dest.writeString(randpic_2);
            dest.writeString(randpic_iphone6);
            dest.writeInt(special_type);
            dest.writeString(ipad_desc);
            dest.writeString(is_publish);
            dest.writeString(mo_type);
            dest.writeInt(type);
            dest.writeString(code);
        }
    }
}
