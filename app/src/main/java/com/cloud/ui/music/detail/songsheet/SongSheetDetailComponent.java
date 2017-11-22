package com.cloud.ui.music.detail.songsheet;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 5:02 PM
 * Editor: ldy
 * Modify Date: 8/22/17 5:02 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, SongSheetDetailModule.class
})
public interface SongSheetDetailComponent {

    void inject(SongSheetDetailFragment mFragment);
}
