package com.cloud.ui.music.local.artist;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: LocalSingerComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 3:21 PM
 * Editor: ldy
 * Modify Date: 9/4/17 3:21 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LocalArtistModule.class
})
public interface LocalArtistComponent {
    void inject(LocalArtistFragment fragment);
}
