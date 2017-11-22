package com.cloud.ui.music.local.album;

import com.cloud.model.music.AlbumInfo;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: LocalAlbumModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 7:41 PM
 * Editor: ldy
 * Modify Date: 9/5/17 7:41 PM
 * Remark:
 */
@Module
public class LocalAlbumModule {
    @Provides
    List<AlbumInfo> providesAlbumInfo() {
        return new ArrayList<>();
    }
}
