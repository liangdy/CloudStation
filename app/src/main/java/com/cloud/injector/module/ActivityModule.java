package com.cloud.injector.module;

import com.cloud.injector.PerActivity;
import com.cloud.ui.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: ActivityModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:19 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:19 PM
 * Remark:
 */
@Module
public class ActivityModule {

    private final BaseActivity mActivity;

    public ActivityModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    public BaseActivity provideActivity() {
        return mActivity;
    }
}
