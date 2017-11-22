package com.cloud.ui.music.local.album;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: LocalAlbumComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 7:42 PM
 * Editor: ldy
 * Modify Date: 9/5/17 7:42 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LocalAlbumModule.class
})
public interface LocalAlbumComponent {
    void inject(LocalAlbumFragment fragment);
}
