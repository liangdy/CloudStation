package com.cloud.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.cloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: CloudStation
 * FileName: NavModel.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/1/17 3:12 PM
 * Editor: ldy
 * Modify Date: 3/1/17 3:12 PM
 * Remark:
 */
public class NavModel implements Parcelable {

    public static final int TYPE_MUSIC = 1;
    public static final int TYPE_VIDEO = 2;
    public static final int TYPE_PICTURE = 3;
    public static final int TYPE_JOKE = 4;

    public String navName;
    public int navResId;
    public int navType;

    protected NavModel() {

    }

    public static List<NavModel> getLocalNavList(Context context) {
        Resources res = context.getResources();
        String[] navNames = res.getStringArray(R.array.nav_array);
        int[] navResId = new int[]{R.drawable.ic_music, R.drawable.ic_video,
                R.drawable.ic_picture, R.drawable.ic_news};
        int[] navType = new int[]{TYPE_MUSIC, TYPE_VIDEO, TYPE_PICTURE, TYPE_JOKE};
        List<NavModel> navModels = new ArrayList<>();
        for (int i = 0; i < navNames.length; i++) {
            NavModel model = new NavModel();
            model.navName = navNames[i];
            model.navResId = navResId[i];
            model.navType = navType[i];
            navModels.add(model);
        }
        return navModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.navName);
        dest.writeInt(this.navResId);
        dest.writeInt(this.navType);
    }

    protected NavModel(Parcel in) {
        this.navName = in.readString();
        this.navResId = in.readInt();
        this.navType = in.readInt();
    }

    public static final Creator<NavModel> CREATOR = new Creator<NavModel>() {
        @Override
        public NavModel createFromParcel(Parcel source) {
            return new NavModel(source);
        }

        @Override
        public NavModel[] newArray(int size) {
            return new NavModel[size];
        }
    };
}
