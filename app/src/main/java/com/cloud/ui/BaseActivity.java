package com.cloud.ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cloud.CloudApplication;
import com.cloud.injector.component.ActivityComponent;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;
import com.magical.library.activity.AbstractActivity;
import com.magical.library.utils.AppManager;

/**
 * Project: CloudStation
 * FileName: BaseActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:21 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:21 PM
 * Remark:
 */
public abstract class BaseActivity extends AbstractActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    protected ActivityComponent mActivityComponent;

    /**
     * 注入Injector
     */
    protected void initInjector() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getApplicationComponent().inject(this);
        initInjector();
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return CloudApplication.getApplication().getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
