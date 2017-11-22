package com.cloud.ui.music.manager;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: ManagerComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 4:26 PM
 * Editor: ldy
 * Modify Date: 8/14/17 4:26 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, ManagerModule.class
})
public interface ManagerComponent {
    void inject(ManagerFragment mFragment);
}