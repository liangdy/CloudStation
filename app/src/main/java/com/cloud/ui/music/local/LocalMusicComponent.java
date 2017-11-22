package com.cloud.ui.music.local;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: LocalMusicComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 4:33 PM
 * Editor: ldy
 * Modify Date: 9/3/17 4:33 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LocalMusicModule.class
})
public interface LocalMusicComponent {
    void inject(LocalMusicActivity activity);
}
