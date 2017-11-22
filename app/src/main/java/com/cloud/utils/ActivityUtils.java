package com.cloud.utils;

import android.content.Context;
import android.content.Intent;

import com.cloud.ui.guide.GuideActivity;
import com.cloud.ui.main.MainActivity;

/**
 * Project: CloudStation
 * FileName: ActivityUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:36 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:36 PM
 * Remark:
 */
public class ActivityUtils {

    private ActivityUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 引导页
     *
     * @param mContext
     */
    public static void goGuideActivity(Context mContext) {
        Intent intent = new Intent(mContext, GuideActivity.class);
        mContext.startActivity(intent);
    }

    /**
     * 主页
     *
     * @param mContext
     */
    public static void goMainActivity(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
}
