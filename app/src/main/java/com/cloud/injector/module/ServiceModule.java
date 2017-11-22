package com.cloud.injector.module;

import android.app.Service;

import com.cloud.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: ServiceModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:21 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:21 PM
 * Remark:
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    public Service provideContext() {
        return mService;
    }
}
