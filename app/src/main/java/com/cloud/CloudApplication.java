package com.cloud;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.cloud.api.CloudClient;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.component.DaggerApplicationComponent;
import com.cloud.injector.module.ApplicationModule;
import com.cloud.utils.StorageUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.magical.library.MagicalApplication;
import com.magical.library.imageloader.FrescoConfigConstants;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Project: CloudStation
 * FileName: CloudStationApplication.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:10 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:10 PM
 * Remark:
 */
public class CloudApplication extends MagicalApplication {

    private static CloudApplication mApplication;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (TextUtils.equals(getCurrentProcessName(this), getPackageName())) {
        }
        initComponent();
        mApplication = this;
        initImageLoader();
        FlowManager.init(this);
    }

    public String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void initComponent() {
        mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    private void initImageLoader() {
        ImagePipelineConfig config = FrescoConfigConstants
                .getImagePipelineConfig(mApplication, StorageUtils
                        .getCacheDir(), StorageUtils
                        .getCacheDir(), CloudClient.getOkHttpClient(-1));
        Fresco.initialize(mApplication, config);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static CloudApplication getApplication() {
        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onTerminate() {
        FlowManager.destroy();
        super.onTerminate();
    }
}
