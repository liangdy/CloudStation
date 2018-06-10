package com.cloud.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cloud.ui.guide.GuideActivity;
import com.cloud.ui.main.MainActivity;
import com.cloud.ui.music.play.PlayActivity;

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
    public static void goMainActivity(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    /**
     * 播放页
     *
     * @param mContext
     * @param bundle
     */
    public static void goPlayActivity(Context mContext, Bundle bundle) {
        Intent intent = new Intent(mContext, PlayActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}
