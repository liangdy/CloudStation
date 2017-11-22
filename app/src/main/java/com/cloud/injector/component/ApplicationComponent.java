package com.cloud.injector.component;

import android.content.Context;

import com.cloud.CloudApplication;
import com.cloud.injector.module.ApplicationModule;
import com.cloud.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: ApplicationComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:09 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:09 PM
 * Remark:
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getContext();

    void inject(CloudApplication mApplication);

    void inject(BaseActivity mBaseActivity);
}
