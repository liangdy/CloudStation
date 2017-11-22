package com.cloud.ui.music.detail.album;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: AlbumComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/29/17 4:36 PM
 * Editor: ldy
 * Modify Date: 8/29/17 4:36 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, AlbumDetailModule.class
})
public interface AlbumDetailComponent {
    void inject(AlbumDetailFragment mFragment);
}
