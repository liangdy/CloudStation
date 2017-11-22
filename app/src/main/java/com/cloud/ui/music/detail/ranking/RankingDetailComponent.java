package com.cloud.ui.music.detail.ranking;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: RankingDetailComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 6:27 PM
 * Editor: ldy
 * Modify Date: 9/1/17 6:27 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, RankingDetailModule.class
})
public interface RankingDetailComponent {
    void inject(RankingDetailFragment mFragment);
}
