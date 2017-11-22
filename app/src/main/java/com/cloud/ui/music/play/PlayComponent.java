package com.cloud.ui.music.play;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;
import com.cloud.ui.BaseActivity;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: PlayComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 3:35 PM
 * Editor: ldy
 * Modify Date: 9/3/17 3:35 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, PlayModule.class
})
public interface PlayComponent {
    void inject(BaseActivity activity);
}
