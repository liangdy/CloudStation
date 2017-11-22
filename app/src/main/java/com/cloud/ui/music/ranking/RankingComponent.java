package com.cloud.ui.music.ranking;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: RankingComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 10:30 AM
 * Editor: ldy
 * Modify Date: 8/14/17 10:30 AM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, RankingModule.class
})
public interface RankingComponent {
    void inject(RankingFragment mFragment);
}
