package com.cloud.ui.music.local.song;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: LocalSongComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 10:25 AM
 * Editor: ldy
 * Modify Date: 9/4/17 10:25 AM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LocalSongModule.class
})
public interface LocalSongComponent {
    void inject(LocalSongFragment fragment);
}
