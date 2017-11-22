package com.cloud.injector.component;

import com.cloud.injector.PerActivity;
import com.cloud.injector.module.ActivityModule;
import com.cloud.ui.BaseActivity;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: ActivityComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:08 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:08 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    BaseActivity getActivity();
}
