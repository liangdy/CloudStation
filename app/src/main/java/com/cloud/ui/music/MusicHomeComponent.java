package com.cloud.ui.music;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: MusicHomeComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/2/17 8:17 PM
 * Editor: ldy
 * Modify Date: 3/2/17 8:17 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, MusicHomeModule.class
})
public interface MusicHomeComponent {
    void inject(MusicHomeFragment mFragment);
}
