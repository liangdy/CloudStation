package com.cloud.ui.music.local.song;

import com.cloud.model.music.MusicInfo;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: LocalSongModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 10:26 AM
 * Editor: ldy
 * Modify Date: 9/4/17 10:26 AM
 * Remark:
 */
@Module
public class LocalSongModule {
    @Provides
    List<MusicInfo> providesMusicInfo() {
        return new ArrayList<>();
    }
}
