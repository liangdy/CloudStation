package com.cloud.injector.component;

import android.app.Service;

import com.cloud.injector.PerService;
import com.cloud.injector.module.ServiceModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: ServiceComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:19 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:19 PM
 * Remark:
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {

    Service getServiceContext();
}
