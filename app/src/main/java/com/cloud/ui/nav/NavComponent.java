package com.cloud.ui.nav;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: MainComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/23/17 10:39 AM
 * Editor: ldy
 * Modify Date: 2/23/17 10:39 AM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, NavModule.class
})
public interface NavComponent {
    void inject(NavFragment mFragment);
}
