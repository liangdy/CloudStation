package com.cloud;

import android.content.Context;

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
        initComponent();
        mApplication = this;
        initImageLoader();
        FlowManager.init(this);
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
