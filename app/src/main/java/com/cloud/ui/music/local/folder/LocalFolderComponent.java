package com.cloud.ui.music.local.folder;

import com.cloud.injector.PerActivity;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;

import dagger.Component;

/**
 * Project: CloudStation
 * FileName: LocalFolderComponent.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/6/17 2:49 PM
 * Editor: ldy
 * Modify Date: 9/6/17 2:49 PM
 * Remark:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LocalFolderModule.class
})
public interface LocalFolderComponent {
    void inject(LocalFolderFragment fragment);
}
