package com.cloud.ui.music.bottom;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: BottomControlComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 12/2/17 4:05 PM
 * Editor: ldy
 * Modify Date: 12/2/17 4:05 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, BottomControlModule.class
})
public interface BottomControlComponent {
    void inject(BottomControlFragment fragment);
}
