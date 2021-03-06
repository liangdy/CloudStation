package com.cloud.ui.music.song;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: RecdSongComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/10/17 2:43 PM
 * Editor: ldy
 * Modify Date: 3/10/17 2:43 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, SongListModule.class
})
public interface SongListComponent {

    void inject(SongListFragment mFragment);
}
