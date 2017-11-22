package com.cloud.ui.music.local.folder;

import com.cloud.model.music.FolderInfo;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: LocalFolderModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/6/17 2:45 PM
 * Editor: ldy
 * Modify Date: 9/6/17 2:45 PM
 * Remark:
 */
@Module
public class LocalFolderModule {
    @Provides
    List<FolderInfo> providesFolderInfo() {
        return new ArrayList<>();
    }
}
