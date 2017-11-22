package com.magical.library;

import android.app.Application;

import com.magical.library.utils.MagicalLog;

/**
 * Project: TShow
 * FileName: MagicalApplication.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class MagicalApplication extends Application {

    private static MagicalApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        MagicalLog.init();
    }

    public static MagicalApplication getContext() {
        return mContext;
    }

}
